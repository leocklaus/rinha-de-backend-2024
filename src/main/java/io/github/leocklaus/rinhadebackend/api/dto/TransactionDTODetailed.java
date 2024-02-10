package io.github.leocklaus.rinhadebackend.api.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record TransactionDTODetailed(int valor, String tipo, String descricao, Instant realizadaEm) {
}
