package edu.uniformix.api.domain.dtos.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierDto(
        @NotBlank
        String name,
        @NotNull
        boolean state) {
}
