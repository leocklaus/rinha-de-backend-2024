package io.github.leocklaus.rinhadebackend.api.dto;

import java.util.List;

public record StatementResponseDTO(BallanceResponseDTO saldo, List<TransactionDTODetailed> ultimasTransacoes) {
}
