package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.batch.BatchDto;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp acquisitionDate;

    @ManyToOne
    @JoinColumn(name = "id_supplier", referencedColumnName = "id")
    private Supplier supplier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    public Batch(BatchDto batchDto, String code) {
        this.batchCode = code;
        this.description = batchDto.description();
        this.quantity = batchDto.quantity();
        this.acquisitionDate = new Timestamp(System.currentTimeMillis());
    }
}
