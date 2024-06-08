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
    private String state;
    private String city;
    private boolean active;

    public Unit(String name, String state, String city, boolean active) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.active = active;
    }

    public Unit(UnitDto unitDto) {
        this.name = unitDto.name();
        this.state = unitDto.state();
        this.city = unitDto.city();
        this.active = true;
    }
}
