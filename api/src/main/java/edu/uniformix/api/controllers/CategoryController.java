package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Category;
import edu.uniformix.api.domain.dtos.category.CategoryDto;
import edu.uniformix.api.domain.dtos.category.CategoryListDto;
import edu.uniformix.api.repositories.CategoryRepository;
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
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryListDto> post(@RequestBody @Valid CategoryDto categoryDto, UriComponentsBuilder uriBuilder) {
        Category category = new Category(categoryDto);
        categoryRepository.save(category);

        var uri = uriBuilder.path("/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryListDto(category));
    }
}
