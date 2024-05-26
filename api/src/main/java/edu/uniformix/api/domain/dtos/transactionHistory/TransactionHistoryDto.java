package edu.uniformix.api.domain.dtos.transactionHistory;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionHistoryDto(
        @NotBlank
        String employeeName,
        @NotBlank
        String unit,
        @NotNull
        int quantity,
        @NotBlank
        String uniform,
        @NotBlank
        String operationType,
        @Email
        String users
) {
}
