package edu.uniformix.api.services;


import com.opencsv.CSVWriter;
import edu.uniformix.api.controllers.BatchController;
import edu.uniformix.api.domain.dtos.batch.BatchListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReportService {
    @Autowired
    private BatchController batchController;
    private final String FILE_PATH = "src/main/java/tpm/batch_report.csv";

    public void writeCsv() {
        try {
            FileWriter fileWriter = new FileWriter(new File(FILE_PATH));
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            List<BatchListDto> data = batchController.fetchData();

            String[] headers = {"Codigo", "Descricao", "Quantidade", "Categoria", "Fornecedor", "Aquisicao"};
            List<String[]> reportData = new ArrayList<>(List.of());
            reportData.add(headers);

            for (BatchListDto batch : data) {
                String[] rowData = {batch.codigo(), batch.descricao(), batch.quantidade().toString(), batch.categoria(),
                        batch.fornecedor(), batch.aquisicao()};
                reportData.add(rowData);
            }

            csvWriter.writeAll(reportData);
            csvWriter.close();
            fileWriter.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

