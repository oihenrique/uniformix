package edu.uniformix.api.domain.dtos.batch;

import edu.uniformix.api.domain.Batch;

import java.sql.Timestamp;

public record BatchListDto(
        String batchCode,
        String description,
        Integer quantity,
        Timestamp aquisitionDate
) {
    public BatchListDto(Batch batch) {
        this(batch.getBatchCode(), batch.getDescription(), batch.getQuantity(), batch.getAquisitionDate());
    }
}
