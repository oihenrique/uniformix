package edu.uniformix.api.domain.dtos.user;

import edu.uniformix.api.domain.User;

public record UserListDto(String id, String name, String login) {
    public UserListDto(User user) {
        this(user.getId(), user.getName(), user.getLogin());
    }
}