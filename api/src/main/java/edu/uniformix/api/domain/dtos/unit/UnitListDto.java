package edu.uniformix.api.domain.dtos.unit;

import edu.uniformix.api.domain.Unit;

public record UnitListDto(String name, String state, String city, boolean active) {
    public UnitListDto(Unit unit) {
        this(unit.getName(), unit.getState(), unit.getCity(), unit.isActive());
    }
}
