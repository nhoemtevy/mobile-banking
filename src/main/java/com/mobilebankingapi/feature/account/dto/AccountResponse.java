package com.mobilebankingapi.feature.account.dto;

import com.mobilebankingapi.domain.UserAccount;
import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;
import com.mobilebankingapi.feature.user.dto.UserResponse;

import java.math.BigDecimal;
import java.util.List;

public record AccountResponse(
        String actName,
        String alias,
        BigDecimal balance,
        AccountTypeResponse accountTypeResponse,
        UserResponse userResponse,
        BigDecimal amount
) {
}
