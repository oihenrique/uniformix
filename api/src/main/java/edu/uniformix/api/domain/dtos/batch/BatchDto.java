package edu.uniformix.api.domain.dtos.batch;

import jakarta.validation.constraints.NotNull;

public record BatchDto(
        String description,
        @NotNull
        Integer quantity,
        String supplier,
        String category
) {
}
