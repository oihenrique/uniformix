package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.batch.BatchDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    private List<Long> uniform = new ArrayList<>();

    public Batch(BatchDto batchDto, String code) {
        this.batchCode = code;
        this.description = batchDto.description();
        this.quantity = batchDto.quantity();
        this.acquisitionDate = new Timestamp(System.currentTimeMillis());
    }
}
