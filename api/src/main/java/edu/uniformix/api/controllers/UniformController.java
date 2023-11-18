package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Uniform;
import edu.uniformix.api.domain.dtos.uniform.UniformDto;
import edu.uniformix.api.domain.dtos.uniform.UniformListDto;
import edu.uniformix.api.repositories.UniformRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("uniform")
public class UniformController {
    @Autowired
    UniformRepository uniformRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UniformListDto> post(@RequestBody @Valid UniformDto uniformDto, UriComponentsBuilder uriBuilder) {
        Uniform uniform = new Uniform(uniformDto);
        uniformRepository.save(uniform);

        var uri = uriBuilder.path("/{id}").buildAndExpand(uniform.getId()).toUri();

        return ResponseEntity.created(uri).body(new UniformListDto(uniform));
    }
}
