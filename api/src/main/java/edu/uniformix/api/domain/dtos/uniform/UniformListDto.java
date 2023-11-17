package edu.uniformix.api.domain.dtos.uniform;

import edu.uniformix.api.domain.Uniform;

public record UniformListDto(String name, Integer quantity, char sex, String size) {
    public UniformListDto(Uniform uniform) {
        this(uniform.getName(), uniform.getQuantity(), uniform.getSex(), uniform.getSize());
    }
}
