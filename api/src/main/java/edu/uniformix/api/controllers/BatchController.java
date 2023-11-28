package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Batch;
import edu.uniformix.api.domain.dtos.batch.BatchDto;
import edu.uniformix.api.domain.dtos.batch.BatchListDto;
import edu.uniformix.api.repositories.BatchRepository;
import edu.uniformix.api.services.CodeService;
import edu.uniformix.api.services.UtilsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("batch")
public class BatchController {
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    CodeService codeService;

    @Transactional
    public ResponseEntity<BatchListDto> post(@RequestBody @Valid BatchDto batchDto, UriComponentsBuilder uriBuilder) {
        String code = codeService.generateCode('B');
        while (codeService.validateBatchCode(code)) {
            code = codeService.generateCode('B');
        }

        Batch batch = new Batch(batchDto, code);
        batchRepository.save(batch);

        var uri = uriBuilder.buildAndExpand("/{id}").toUri();

        return ResponseEntity.created(uri).body(new BatchListDto(batch));
    }

    @GetMapping
    public ResponseEntity<List<BatchListDto>> list(@PageableDefault(sort = "acquisitionDate") Pageable paginate) {
        Page<BatchListDto> batchPage = batchRepository.findAll(paginate).map(BatchListDto::new);
        List<BatchListDto> batchList = batchPage.getContent();

        return ResponseEntity.ok(batchList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        Batch batch = batchRepository.findById(id).orElse(null);

        if (batch == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch not found");
        }

        return ResponseEntity.ok(new BatchListDto(batch));
    }

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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Batch batch = batchRepository.findById(id).orElse(null);

        if (batch == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch not found");
        }

        batchRepository.delete(batch);

        return ResponseEntity.noContent().build();
    }
}