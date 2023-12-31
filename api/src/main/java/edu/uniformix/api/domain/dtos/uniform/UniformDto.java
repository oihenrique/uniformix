package edu.uniformix.api.domain.dtos.uniform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UniformDto(
        @NotBlank
        String name,
        @NotNull
        Integer quantity,
        String sex,
        @NotBlank
        String size) {
}
