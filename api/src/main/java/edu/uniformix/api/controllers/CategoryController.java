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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
    public ResponseEntity<List<CategoryListDto>> list(@PageableDefault(sort = "name") Pageable paginate) {
        Page<CategoryListDto> categoryPage = categoryRepository.findAll(paginate).map(CategoryListDto::new);
        List<CategoryListDto> categoryList = categoryPage.getContent();

        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CategoryListDto(category));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        UtilsService.copyNonNullProperties(categoryDto, category);
        return ResponseEntity.ok().body(categoryRepository.save(category));
    }

    @DeleteMapping("/{name}")
    @Transactional
    public ResponseEntity<Object> inactivate(@PathVariable String name) {
        Category category = categoryRepository.findByName(name);

        if (category == null) {
            return ResponseEntity.notFound().build();
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
            return ResponseEntity.notFound().build();
        }

        categoryRepository.delete(category);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Object> updateCategoryState(@RequestBody @Valid CategoryDto categoryDto, @PathVariable String name) {
        Category category = categoryRepository.findByName(name);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        if (category.isState() == categoryDto.state()) {
            return ResponseEntity.badRequest().body("Category already in the requested state");
        }

        category.setState(categoryDto.state());
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }
}
