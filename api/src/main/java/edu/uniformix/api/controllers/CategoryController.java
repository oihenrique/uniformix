package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Category;
import edu.uniformix.api.domain.dtos.category.CategoryDto;
import edu.uniformix.api.domain.dtos.category.CategoryListDto;
import edu.uniformix.api.repositories.CategoryRepository;
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

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryListDto> post(@RequestBody @Valid CategoryDto categoryDto, UriComponentsBuilder uriBuilder) {
        Category category = new Category(categoryDto);
        categoryRepository.save(category);

        var uri = uriBuilder.path("/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryListDto(category));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryListDto>> list(@PageableDefault(sort = "name") Pageable paginate) {
        Page<CategoryListDto> categoryList = categoryRepository.findAll(paginate).map(CategoryListDto::new);

        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
        }

        return ResponseEntity.ok(new CategoryListDto(category));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
        }

        UtilsService.copyNonNullProperties(categoryDto, category);
        return ResponseEntity.ok().body(categoryRepository.save(category));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> inactivate(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
        }

        category.setState(false);
        categoryRepository.save(category);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
        }

        categoryRepository.delete(category);

        return ResponseEntity.noContent().build();
    }
}