package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.*;
import edu.uniformix.api.domain.dtos.transactionHistory.TransactionHistoryDto;
import edu.uniformix.api.domain.dtos.transactionHistory.TransactionHistoryListDto;
import edu.uniformix.api.repositories.*;
import edu.uniformix.api.services.CodeService;
import edu.uniformix.api.services.ProtocolPdfService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionHistoryController {
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UniformRepository uniformRepository;
    @Autowired
    BatchRepository batchRepository;

    ProtocolPdfService protocolPdfService = new ProtocolPdfService();

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid TransactionHistoryDto transactionHistoryDto,
                     UriComponentsBuilder uriBuilder,
                     HttpServletResponse response) throws IOException {
        TransactionHistory transaction = new TransactionHistory(transactionHistoryDto);
        int updatedUniformQuantity;
        int updatedBatchQuantity;

        transaction.setProtocolNumber(CodeService.generateProtocolNumber());

        Uniform uniform = uniformRepository.findByName(transactionHistoryDto.uniform());
        Batch batch = batchRepository.findByCode(uniform.getBatch().getBatchCode());

        if (uniform == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Uniform not found");
            return;
        }

        Unit unit = unitRepository.findByName(transactionHistoryDto.unit());
        if (unit == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unit not found");
            return;
        }

        Users user = userRepository.findUserByEmail(transactionHistoryDto.users());
        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
            return;
        }

        if ("retirada".equalsIgnoreCase(transactionHistoryDto.operationType())) {
            updatedUniformQuantity = uniform.getQuantity() - transaction.getQuantity();
            updatedBatchQuantity = batch.getQuantity() - transaction.getQuantity();

            if (updatedUniformQuantity < 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Insufficient uniform quantity");
                return;
            }
        } else {
            updatedUniformQuantity = uniform.getQuantity() + transaction.getQuantity();
            updatedBatchQuantity = batch.getQuantity() + transaction.getQuantity();
        }

        batch.setQuantity(updatedBatchQuantity);
        uniform.setQuantity(updatedUniformQuantity);

        batchRepository.save(batch);
        uniformRepository.save(uniform);

        transaction.setUniform(uniform);
        transaction.setUnit(unit);
        transaction.setUsers(user);

        transactionHistoryRepository.save(transaction);

        if ("retirada".equalsIgnoreCase(transactionHistoryDto.operationType())) {
            byte[] pdfBytes = protocolPdfService.generateTransactionPDF(
                    transactionHistoryDto.employeeName(),
                    transaction.getProtocolNumber(),
                    transactionHistoryDto.uniform(),
                    transactionHistoryDto.quantity(),
                    unit.getState(),
                    unit.getCity()
            );

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + transactionHistoryDto.employeeName() + " - protocolo.pdf");
            response.getOutputStream().write(pdfBytes);
        }
    }

    @GetMapping
    public ResponseEntity<List<TransactionHistoryListDto>> list(@PageableDefault(sort = "date", direction = Sort.Direction.ASC) Pageable paginate) {
        Page<TransactionHistoryListDto> transactionHistoryPage = transactionHistoryRepository.findAll(paginate).map(TransactionHistoryListDto::new);
        List<TransactionHistoryListDto> transactionHistoryList = transactionHistoryPage.getContent();

        return ResponseEntity.ok(transactionHistoryList);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<List<TransactionHistoryListDto>> searchList(@PathVariable String search, @PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable paginate) {
        Page<TransactionHistoryListDto> transactionHistoryPage = transactionHistoryRepository.findByEmployeeName(search.toLowerCase(), paginate).map(TransactionHistoryListDto::new);
        List<TransactionHistoryListDto> transactionHistoryList = transactionHistoryPage.getContent();

        if (transactionHistoryList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(transactionHistoryList);
        }
    }
}
