package edu.uniformix.api.controllers;

import edu.uniformix.api.FileStorageProperties;
import edu.uniformix.api.domain.Batch;
import edu.uniformix.api.domain.Category;
import edu.uniformix.api.domain.Supplier;
import edu.uniformix.api.domain.Uniform;
import edu.uniformix.api.domain.dtos.batch.BatchDto;
import edu.uniformix.api.domain.dtos.batch.BatchListDto;
import edu.uniformix.api.domain.dtos.uniform.UniformDto;
import edu.uniformix.api.repositories.BatchRepository;
import edu.uniformix.api.repositories.CategoryRepository;
import edu.uniformix.api.repositories.SupplierRepository;
import edu.uniformix.api.repositories.UniformRepository;
import edu.uniformix.api.services.CodeService;
import edu.uniformix.api.services.CsvReportService;
import edu.uniformix.api.services.UtilsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("batch")
public class BatchController {
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UniformRepository uniformRepository;
    @Autowired
    CodeService codeService;
    @Autowired
    CsvReportService csvReportService;

    private final Path fileStorageLocation;

    public BatchController(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getDownload()).toAbsolutePath().normalize();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BatchListDto> post(@RequestBody @Valid BatchDto batchDto, UriComponentsBuilder uriBuilder) {
        String code = codeService.generateCode('B');
        while (codeService.validateBatchCode(code)) {
            code = codeService.generateCode('B');
        }

        Supplier supplier = supplierRepository.findByName(batchDto.supplier());
        Category category = categoryRepository.findByName(batchDto.category());

        Batch batch = new Batch(batchDto, code);
        batch.setSupplier(supplier);
        batch.setCategory(category);

        for (UniformDto uniformDto : batchDto.uniform()) {
            Uniform uniform = new Uniform(uniformDto);
            uniform.setBatch(batch);
            uniformRepository.save(uniform);
            batch.getUniform().add(uniform);
        }

        batchRepository.save(batch);

        var uri = uriBuilder.buildAndExpand("/{id}").toUri();

        return ResponseEntity.created(uri).body(new BatchListDto(batch));
    }

    @GetMapping()
    public ResponseEntity<List<BatchListDto>> list(@PageableDefault(sort = "acquisitionDate", direction = Sort.Direction.DESC) Pageable paginate) {
        Page<BatchListDto> batchPage = batchRepository.findAll(paginate).map(BatchListDto::new);
        List<BatchListDto> batchList = batchPage.getContent();

        return ResponseEntity.ok(batchList);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<List<BatchListDto>> searchList(@PathVariable String search, @PageableDefault(sort = "acquisitionDate") Pageable paginate) {
        try {
            Page<BatchListDto> batchPage = batchRepository.findByText(search.toLowerCase(), paginate).map(BatchListDto::new);
            List<BatchListDto> batchList = batchPage.getContent();

            if (batchList.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(batchList);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/info/totalInventory")
    public ResponseEntity<Long> countTotalInventory() {
        return ResponseEntity.ok(batchRepository.countTotalRows());
    }

    @GetMapping("/report/fetchData")
    public List<BatchListDto> fetchData() {
        int pageSize = Integer.parseInt(String.valueOf(countTotalInventory().getBody()));

        ResponseEntity<List<BatchListDto>> responseEntity = list(PageRequest.of(0, pageSize));
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/report/downloadFile/{fileName:.+}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable String fileName) {
        String contentType = "application/octet-stream";

        String downloadName = UtilsService.dateFormatter(Timestamp.valueOf(LocalDateTime.now())) + "-relatorio-estoque.csv";

        byte[] batchReport = csvReportService.writeCsv();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadName + "\"")
                .header("Access-Control-Expose-Headers", "Content-Disposition")
                .body(batchReport);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> get(@PathVariable Long id) {
//        Batch batch = batchRepository.findById(id).orElse(null);
//
//        if (batch == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch not found");
//        }
//
//        return ResponseEntity.ok(new BatchListDto(batch));
//    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@RequestBody BatchDto batchDto, @PathVariable Long id) {
        Batch batch = batchRepository.findById(id).orElse(null);

        if (batch == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch not found");
        }

        UtilsService.copyNonNullProperties(batchDto, batch);
        return ResponseEntity.ok().body(batchRepository.save(batch));
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Object> delete(@PathVariable Long id) {
//        Batch batch = batchRepository.findById(id).orElse(null);
//
//        if (batch == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch not found");
//        }
//
//        batchRepository.delete(batch);
//
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{code}")
    @Transactional
    public ResponseEntity<Object> deleteByCode(@PathVariable String code) {
        Batch batch = batchRepository.findByCode(code);

        if (batch == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch not found");
        }

        batchRepository.delete(batch);

        return ResponseEntity.noContent().build();
    }
}