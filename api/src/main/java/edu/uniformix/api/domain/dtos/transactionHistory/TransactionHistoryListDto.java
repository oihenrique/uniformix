package edu.uniformix.api.domain.dtos.transactionHistory;

import edu.uniformix.api.domain.TransactionHistory;

import java.time.LocalDateTime;

public record TransactionHistoryListDto(
        String id,
        String protocolNumber,
        String uniform,
        String employeeName,
        String unit,
        int quantity,
        String operationType,
        String users,
        LocalDateTime timestamp
) {
    public TransactionHistoryListDto(TransactionHistory transactionHistory) {
        this(transactionHistory.getId(),
                transactionHistory.getProtocolNumber(),
                transactionHistory.getUniform().getName(),
                transactionHistory.getEmployeeName(),
                transactionHistory.getUnit().getName(),
                transactionHistory.getQuantity(),
                transactionHistory.getOperationType(),
                transactionHistory.getUsers().getName(),
                transactionHistory.getDate());
    }
}
