package io.github.leocklaus.rinhadebackend.api.dto;

import java.time.LocalDateTime;

public record BallanceResponseDTO(int total, LocalDateTime dataExtrato, int limite) {
}
