package edu.uniformix.api.domain.dtos.batch;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BatchDto(
        String description,
        @NotNull
        Integer quantity,
        String supplier,
        String category,
        List<Long> uniform
) {
}
