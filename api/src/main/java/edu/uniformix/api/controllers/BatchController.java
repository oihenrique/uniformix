package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Batch;
import edu.uniformix.api.domain.dtos.batch.BatchDto;
import edu.uniformix.api.domain.dtos.batch.BatchListDto;
import edu.uniformix.api.repositories.BatchRepository;
import edu.uniformix.api.services.CodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("batch")
public class BatchController {
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    CodeService codeService;

    @PostMapping
    public ResponseEntity<BatchListDto> post(@RequestBody @Valid BatchDto batchDto, UriComponentsBuilder uriBuilder) {
        String code = codeService.generateCode('S');
        while(codeService.validateBatchCode(code)) {
            code = codeService.generateCode('S');
        }

        Batch batch = new Batch(batchDto, code);
        batchRepository.save(batch);

        var uri = uriBuilder.buildAndExpand("/{id}").toUri();

        return ResponseEntity.created(uri).body(new BatchListDto(batch));
    }
}
