package com.mobilebankingapi.feature.transaction;

import com.mobilebankingapi.feature.transaction.dto.TransactionCreateRequest;
import com.mobilebankingapi.feature.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest) {
        return transactionService.transfer(transactionCreateRequest);
    }

    @GetMapping
    Page<TransactionResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection
    ){
        return transactionService.findAll(page, size, sortDirection);
    }

}
