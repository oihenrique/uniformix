package edu.uniformix.api.domain.dtos.supplier;

import edu.uniformix.api.domain.Supplier;

public record SupplierListDto(String code, String name, boolean state) {
    public SupplierListDto(Supplier supplier) {
        this(supplier.getCode(), supplier.getName(), supplier.isState());
    }
}
