package me.gabriel.authentication.model.dto;

import java.time.LocalDateTime;

public record LoginResponseDTO(String token, LocalDateTime expirationTime) {

}
