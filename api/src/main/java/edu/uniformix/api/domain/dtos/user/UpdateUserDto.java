package edu.uniformix.api.domain.dtos.user;

import edu.uniformix.api.domain.User;

public record UpdateUserDto(String id, String name, String login, String password) {
    public UpdateUserDto(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword());
    }
}