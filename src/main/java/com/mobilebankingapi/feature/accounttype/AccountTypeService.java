package com.mobilebankingapi.feature.accounttype;

import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findList();
    AccountTypeResponse findByAlias(String alias);

}
