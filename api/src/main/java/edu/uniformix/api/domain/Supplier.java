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

    public Supplier(String code, String name, boolean state) {
        this.code = code;
        this.name = name;
        this.state = state;
    }

    public Supplier(String code, String name) {
        this.code = code;
        this.name = name;
        this.state = true;
    }
}
