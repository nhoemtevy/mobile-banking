package com.mobilebankingapi.mapper;

import com.mobilebankingapi.domain.AccountType;
import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    List<AccountTypeResponse> toListAccountTypeResponse(List<AccountType> accountTypes);

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
}
