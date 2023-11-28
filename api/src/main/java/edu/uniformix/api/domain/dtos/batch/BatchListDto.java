package edu.uniformix.api.domain.dtos.batch;

import edu.uniformix.api.domain.Batch;
import edu.uniformix.api.domain.Category;
import edu.uniformix.api.domain.Supplier;

import java.sql.Timestamp;

public record BatchListDto(
        String codigo,
        String descricao,
        Integer quantidade,
        String fornecedor,
        String categoria,
        Timestamp aquisicao
) {
    public BatchListDto(Batch batch) {
        this(batch.getBatchCode(), batch.getDescription(), batch.getQuantity(), batch.getSupplier().getName(), batch.getCategory().getName(), batch.getAcquisitionDate());
    }
}
