package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.transactionHistory.TransactionHistoryDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor @NoArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String protocolNumber;

    @OneToOne
    @JoinColumn(name = "id_uniform", referencedColumnName = "id")
    private Uniform uniform;

    private String employeeName;

    @ManyToOne
    @JoinColumn(name = "id_unit", referencedColumnName = "id")
    private Unit unit;

    private int quantity;

    private String operationType;

    @ManyToOne
    @JoinColumn(name = "id_users", referencedColumnName = "id")
    private Users users;

    private LocalDateTime date;

    public TransactionHistory(TransactionHistoryDto transactionHistoryDto) {
        this.employeeName = transactionHistoryDto.employeeName();
        this.quantity = transactionHistoryDto.quantity();
        this.operationType = transactionHistoryDto.operationType();
        this.date = LocalDateTime.now();
    }
}
