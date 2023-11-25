package edu.uniformix.api.domain.dtos.user;

import edu.uniformix.api.domain.Users;

public record UpdateUserDto(String id, String name, String login, String password) {
    public UpdateUserDto(Users users) {
        this(users.getId(), users.getName(), users.getLogin(), users.getPassword());
    }
}