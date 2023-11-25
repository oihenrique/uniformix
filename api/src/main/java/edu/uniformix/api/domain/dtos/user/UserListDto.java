package edu.uniformix.api.domain.dtos.user;

import edu.uniformix.api.domain.Users;

public record UserListDto(String id, String name, String login) {
    public UserListDto(Users users) {
        this(users.getId(), users.getName(), users.getLogin());
    }
}