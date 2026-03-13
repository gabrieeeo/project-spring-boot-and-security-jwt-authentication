package me.gabriel.authentication.model.dto;

import me.gabriel.authentication.model.UserRole;

public record RegisterDTO(String username, String email, String password, UserRole role) {

}
