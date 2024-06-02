package edu.uniformix.api.services;

import com.opencsv.CSVWriter;
import edu.uniformix.api.controllers.BatchController;
import edu.uniformix.api.domain.dtos.batch.BatchListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReportService {
    @Autowired
    private BatchController batchController;

    public byte[] writeCsv() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter osWriter = new OutputStreamWriter(baos, StandardCharsets.UTF_8);
        CSVWriter writer = new CSVWriter(osWriter);

        try {
            List<BatchListDto> data = batchController.fetchData();

            String[] headers = {"Codigo", "Descricao", "Quantidade", "Categoria", "Fornecedor", "Aquisicao"};
            List<String[]> reportData = new ArrayList<>();
            reportData.add(headers);

            for (BatchListDto batch : data) {
                String[] rowData = {batch.codigo(), batch.descricao(), batch.quantidade().toString(), batch.categoria(),
                        batch.fornecedor(), batch.aquisicao()};
                reportData.add(rowData);
            }

            writer.writeAll(reportData);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
