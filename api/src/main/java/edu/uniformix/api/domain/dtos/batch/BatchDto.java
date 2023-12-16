package edu.uniformix.api.domain.dtos.batch;

import edu.uniformix.api.domain.dtos.uniform.UniformDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BatchDto(
        String description,
        @NotNull
        Integer quantity,
        String supplier,
        String category,
        List<UniformDto> uniform
) {
}
