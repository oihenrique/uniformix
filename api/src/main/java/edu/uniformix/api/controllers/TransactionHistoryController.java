package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.TransactionHistory;
import edu.uniformix.api.domain.Uniform;
import edu.uniformix.api.domain.Unit;
import edu.uniformix.api.domain.Users;
import edu.uniformix.api.domain.dtos.transactionHistory.TransactionHistoryDto;
import edu.uniformix.api.domain.dtos.transactionHistory.TransactionHistoryListDto;
import edu.uniformix.api.repositories.TransactionHistoryRepository;
import edu.uniformix.api.repositories.UniformRepository;
import edu.uniformix.api.repositories.UnitRepository;
import edu.uniformix.api.repositories.UserRepository;
import edu.uniformix.api.services.CodeService;
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

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionHistoryListDto> post(@RequestBody @Valid TransactionHistoryDto transactionHistoryDto, UriComponentsBuilder uriBuilder) {
        TransactionHistory transaction = new TransactionHistory(transactionHistoryDto);

        transaction.setProtocolNumber(CodeService.generateProtocolNumber());

        Uniform uniform = uniformRepository.findByName(transactionHistoryDto.uniform());
        if (uniform == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Unit unit = unitRepository.findByName(transactionHistoryDto.unit());
        if (unit == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Users user = userRepository.findUserByEmail(transactionHistoryDto.user());
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        transaction.setUniform(uniform);
        transaction.setUnit(unit);
        transaction.setUsers(user);

        transactionHistoryRepository.save(transaction);

        var uri = uriBuilder.path("/transaction/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransactionHistoryListDto(transaction));
    }


    @GetMapping
    public ResponseEntity<List<TransactionHistoryListDto>> list(@PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable paginate) {
        Page<TransactionHistoryListDto> transactionHistoryPage = transactionHistoryRepository.findAll(paginate).map(TransactionHistoryListDto::new);
        List<TransactionHistoryListDto> transactionHistoryList = transactionHistoryPage.getContent();

        return ResponseEntity.ok(transactionHistoryList);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<List<TransactionHistoryListDto>> searchList(@PathVariable String search, @PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable paginate) {
        try {
            Page<TransactionHistoryListDto> transactionHistoryPage = transactionHistoryRepository.findByEmployeeName(search.toLowerCase(), paginate).map(TransactionHistoryListDto::new);
            List<TransactionHistoryListDto> transactionHistoryList = transactionHistoryPage.getContent();

            if (transactionHistoryList.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(transactionHistoryList);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
