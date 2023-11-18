package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Supplier;
import edu.uniformix.api.domain.dtos.supplier.SupplierDto;
import edu.uniformix.api.domain.dtos.supplier.SupplierListDto;
import edu.uniformix.api.repositories.SupplierRepository;
import edu.uniformix.api.services.CodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

        String code = codeService.generateCode();
        while(codeService.validateSupplyCode(code)) {
          code = codeService.generateCode();
        }

        Supplier supplier = new Supplier(supplierDto, code);
        supplierRepository.save(supplier);

        var uri = uriBuilder.path("/{id}").buildAndExpand(supplier.getId()).toUri();

        return ResponseEntity.created(uri).body(new SupplierListDto(supplier));
    }
}
