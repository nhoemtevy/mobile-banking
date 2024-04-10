package com.mobilebankingapi.feature.transaction.dto;

import com.mobilebankingapi.feature.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(

        AccountSnippetResponse owner,

        AccountSnippetResponse transferReceiver,

        BigDecimal amount,

        String remark,

        String transactionType,

        Boolean status,

        LocalDateTime transactionAt

) {
}
