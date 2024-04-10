package com.mobilebankingapi.feature.transaction;

import com.mobilebankingapi.feature.transaction.dto.TransactionCreateRequest;
import com.mobilebankingapi.feature.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);

    Page<TransactionResponse> findAll(int page, int size, String sortDirection );
}
