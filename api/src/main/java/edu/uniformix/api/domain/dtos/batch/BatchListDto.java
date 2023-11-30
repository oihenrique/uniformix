package edu.uniformix.api.domain.dtos.batch;

import edu.uniformix.api.domain.Batch;
import edu.uniformix.api.services.UtilsService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public record BatchListDto(
        String codigo,
        String descricao,
        Integer quantidade,
        String fornecedor,
        String categoria,
        String aquisicao
) {
    public BatchListDto(Batch batch) {
        this(batch.getBatchCode(),
                batch.getDescription(),
                batch.getQuantity(),
                batch.getSupplier().getName(),
                batch.getCategory().getName(),
                UtilsService.dateFormatter(batch.getAcquisitionDate()));
    }
}
