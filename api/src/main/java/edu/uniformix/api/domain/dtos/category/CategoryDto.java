package edu.uniformix.api.domain.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDto(
        @NotBlank
        String name,
        @NotNull
        Boolean state) {
}
