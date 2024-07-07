package edu.uniformix.api.domain.dtos.uniform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record UniformDto(
        @NotBlank
        String name,
        @NotNull @PositiveOrZero
        Integer quantity,
        String sex,
        @NotBlank
        String size) {
}
