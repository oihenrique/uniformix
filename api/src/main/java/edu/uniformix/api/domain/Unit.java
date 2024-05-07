package edu.uniformix.api.domain;

import edu.uniformix.api.domain.dtos.unit.UnitDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active;

    public Unit(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public Unit(UnitDto unitDto) {
        this.name = unitDto.name();
        this.active = true;
    }
}
