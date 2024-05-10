package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Unit;
import edu.uniformix.api.domain.dtos.unit.UnitDto;
import edu.uniformix.api.domain.dtos.unit.UnitListDto;
import edu.uniformix.api.repositories.UnitRepository;
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
@RequestMapping("unit")
public class UnitController {
    @Autowired
    UnitRepository unitRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UnitListDto> post(@RequestBody @Valid UnitDto unitDto, UriComponentsBuilder uriBuilder) {
        Unit unit = new Unit(unitDto);

        unitRepository.save(unit);

        var uri = uriBuilder.buildAndExpand("/{id}").toUri();

        return ResponseEntity.created(uri).body(new UnitListDto(unit));
    }

    @GetMapping
    public ResponseEntity<List<UnitListDto>> list(@PageableDefault(sort = "name")Pageable paginate) {
        Page<UnitListDto> unitPage = unitRepository.findAll(paginate).map(UnitListDto::new);
        List<UnitListDto> unitList = unitPage.getContent();

        return ResponseEntity.ok(unitList);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> inativateByName(@PathVariable String name) {
        Unit unit = unitRepository.findByName(name);

        if (unit == null) {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Unit not found");
        }

        unit.setActive(false);

        unitRepository.save(unit);

        return ResponseEntity.noContent().build();
    }
}
