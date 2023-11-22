package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.batch.BatchDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String batchCode;
    private String description;
    private int quantity;
    private Timestamp aquisitionDate;
    @ManyToOne
    @JoinColumn(name = "supplier", referencedColumnName = "id")
    private Supplier supplier;
    @OneToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    public Batch(BatchDto batchDto, String code) {
        this.batchCode = code;
        this.description = batchDto.description();
        this.quantity = batchDto.quantity();
        this.aquisitionDate = new Timestamp(System.currentTimeMillis());
    }
}
