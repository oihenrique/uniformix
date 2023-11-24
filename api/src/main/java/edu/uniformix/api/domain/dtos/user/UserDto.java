package edu.uniformix.api.domain.dtos.user;

import edu.uniformix.api.domain.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotBlank String name,
        @NotBlank @Email String login,
        @NotBlank String password,
        @NotNull UserRoles role) {
}