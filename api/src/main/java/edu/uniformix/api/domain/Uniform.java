package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.uniform.UniformDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uniform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private String sex;
    private String size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_batch", referencedColumnName = "id")
    private Batch batch;

    public Uniform(UniformDto uniformDto) {
        this.name = uniformDto.name();
        this.quantity = uniformDto.quantity();
        this.sex = uniformDto.sex();
        this.size = uniformDto.size();
    }
}
