package edu.uniformix.api.domain.dtos.unit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnitDto(
        @NotBlank
        String name,
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotNull
        boolean active
) {
}
