package com.mobilebankingapi.mapper;

import com.mobilebankingapi.domain.Transaction;
import com.mobilebankingapi.feature.transaction.dto.TransactionCreateRequest;
import com.mobilebankingapi.feature.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {

    TransactionResponse toTransactionResponse(Transaction transaction);

    Transaction fromTransactionCreateRequest(TransactionCreateRequest transactionCreateRequest);

    List<TransactionResponse> toTransactionResponse (List<Transaction> transaction);
}
