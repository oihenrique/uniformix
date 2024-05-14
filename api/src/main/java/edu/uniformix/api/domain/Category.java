package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.category.CategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor @NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean state;

    public Category(CategoryDto categoryDto) {
        this.name = categoryDto.name();
        this.state = true;
    }
}
