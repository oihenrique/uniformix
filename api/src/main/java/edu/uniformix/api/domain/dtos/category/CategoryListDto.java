package edu.uniformix.api.domain.dtos.category;

import edu.uniformix.api.domain.Category;

public record CategoryListDto(String name, Boolean state) {
    public CategoryListDto (Category category){
        this(category.getName(), category.isState());
    }
}
