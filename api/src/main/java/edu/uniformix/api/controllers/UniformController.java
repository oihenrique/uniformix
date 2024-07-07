package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Uniform;
import edu.uniformix.api.domain.dtos.uniform.UniformDto;
import edu.uniformix.api.domain.dtos.uniform.UniformListDto;
import edu.uniformix.api.repositories.UniformRepository;
import edu.uniformix.api.services.UtilsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("uniform")
public class UniformController {
    @Autowired
    private UniformRepository uniformRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UniformListDto> post(@RequestBody @Valid UniformDto uniformDto, UriComponentsBuilder uriBuilder) {
        Uniform uniform = new Uniform(uniformDto);
        uniformRepository.save(uniform);

        var uri = uriBuilder.path("/{id}").buildAndExpand(uniform.getId()).toUri();

        return ResponseEntity.created(uri).body(new UniformListDto(uniform));
    }

    @GetMapping
    public ResponseEntity<List<UniformListDto>> list(@PageableDefault(sort = "name") Pageable paginate) {
        Page<UniformListDto> uniformPage = uniformRepository.findAll(paginate).map(UniformListDto::new);
        List<UniformListDto> uniformList = uniformPage.getContent();

        return ResponseEntity.ok(uniformList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        Uniform uniform = uniformRepository.findById(id).orElse(null);

        if (uniform == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UniformListDto(uniform));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@RequestBody UniformDto uniformDto, @PathVariable Long id) {
        Uniform uniform = uniformRepository.findById(id).orElse(null);

        if (uniform == null) {
            return ResponseEntity.notFound().build();
        }

        UtilsService.copyNonNullProperties(uniformDto, uniform);
        return ResponseEntity.ok().body(uniformRepository.save(uniform));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Uniform uniform = uniformRepository.findById(id).orElse(null);

        if (uniform == null) {
            return ResponseEntity.notFound().build();
        }

        uniformRepository.delete(uniform);

        return ResponseEntity.noContent().build();
    }
}
