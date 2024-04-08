package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.feature.account.dto.AccountCreateRequest;
import com.mobilebankingapi.feature.account.dto.AccountRenameRequest;
import com.mobilebankingapi.feature.account.dto.AccountResponse;
import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findAccountByAccountNumber(String accountNumber);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);
    void hiddenAccount(String actNo);
    Page<AccountResponse> findList(int page, int size);
}
