package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.supplier.SupplierDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor @NoArgsConstructor
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    //private List<Object> batchList;
    private boolean state;

    public Supplier(SupplierDto supplierDto, String code) {
        this.code = code;
        this.name = supplierDto.name();
        this.state = supplierDto.state();
    }
}
