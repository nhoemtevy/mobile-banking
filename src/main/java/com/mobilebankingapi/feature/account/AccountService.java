package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.feature.account.dto.AccountCreateRequest;
import com.mobilebankingapi.feature.account.dto.AccountResponse;
import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findAccountByAccountNumber(String accountNumber);
}
