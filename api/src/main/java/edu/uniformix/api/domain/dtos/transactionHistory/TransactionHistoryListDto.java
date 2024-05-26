package edu.uniformix.api.domain.dtos.transactionHistory;

import edu.uniformix.api.domain.TransactionHistory;

import java.time.LocalDateTime;

public record TransactionHistoryListDto(
        String protocolNumber,
        String uniform,
        String employeeName,
        String unit,
        int quantity,
        String operationType,
        String users,
        String dateTime
) {
    public TransactionHistoryListDto(TransactionHistory transactionHistory) {
        this(transactionHistory.getProtocolNumber(),
                transactionHistory.getUniform().getName(),
                transactionHistory.getEmployeeName(),
                transactionHistory.getUnit().getName(),
                transactionHistory.getQuantity(),
                transactionHistory.getOperationType(),
                transactionHistory.getUsers().getName(),
                transactionHistory.getDate());
    }
}
