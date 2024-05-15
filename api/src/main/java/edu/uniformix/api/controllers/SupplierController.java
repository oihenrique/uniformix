package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Supplier;
import edu.uniformix.api.domain.Unit;
import edu.uniformix.api.domain.dtos.supplier.SupplierDto;
import edu.uniformix.api.domain.dtos.supplier.SupplierListDto;
import edu.uniformix.api.domain.dtos.unit.UnitDto;
import edu.uniformix.api.repositories.SupplierRepository;
import edu.uniformix.api.services.CodeService;
import edu.uniformix.api.services.UtilsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CodeService codeService;

    @PostMapping
    @Transactional
    public ResponseEntity<SupplierListDto> post(@RequestBody @Valid SupplierDto supplierDto, UriComponentsBuilder uriBuilder) {

        String generatedCode = codeService.generateCode('S');
        while (codeService.validateSupplyCode(generatedCode)) {
            generatedCode = codeService.generateCode('S');
        }

        Supplier supplier = new Supplier(generatedCode, supplierDto.name());
        supplierRepository.save(supplier);

        var uri = uriBuilder.path("/{id}").buildAndExpand(supplier.getId()).toUri();

        return ResponseEntity.created(uri).body(new SupplierListDto(supplier));
    }

    @GetMapping
    public ResponseEntity<List<SupplierListDto>> list(@PageableDefault(sort = "name") Pageable paginate) {
        Page<SupplierListDto> supplierPage = supplierRepository.findAll(paginate).map(SupplierListDto::new);
        List<SupplierListDto> supplierList = supplierPage.getContent();

        return ResponseEntity.ok(supplierList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);

        if (supplier == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Supplier not found");
        }

        return ResponseEntity.ok(new SupplierListDto(supplier));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@RequestBody SupplierDto supplierDto, @PathVariable Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);

        if (supplier == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Supplier not found");
        }

        UtilsService.copyNonNullProperties(supplierDto, supplier);
        return ResponseEntity.ok().body(supplierRepository.save(supplier));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> inactivate(@PathVariable Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);

        if (supplier == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Supplier not found");
        }

        supplier.setState(false);

        supplierRepository.save(supplier);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);

        if (supplier == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Supplier not found");
        }

        supplierRepository.delete(supplier);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Object> updateSupplierState(@RequestBody @Valid SupplierDto supplierDto, @PathVariable String name) {
        Supplier supplier = supplierRepository.findByName(name);

        if (supplier == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        }

        if (supplier.isState() == supplierDto.state()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Supplier already in the requested state");
        }

        try {
            supplier.setState(supplierDto.state());
            supplierRepository.save(supplier);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update supplier state");
        }
    }
}