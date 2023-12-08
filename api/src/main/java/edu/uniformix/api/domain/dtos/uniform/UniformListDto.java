package edu.uniformix.api.domain.dtos.uniform;

import edu.uniformix.api.domain.Uniform;

public record UniformListDto(Long id, String name, Integer quantity, String sex, String size) {
    public UniformListDto(Uniform uniform) {
        this(uniform.getId(),
                uniform.getName(),
                uniform.getQuantity(),
                uniform.getSex(),
                uniform.getSize());
    }
}
