package edu.uniformix.api.domain.dtos.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(@NotBlank @Email String login, @NotBlank String password) {
}