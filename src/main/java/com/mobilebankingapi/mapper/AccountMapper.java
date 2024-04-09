package com.mobilebankingapi.mapper;
import com.mobilebankingapi.domain.Account;
import com.mobilebankingapi.domain.AccountType;
import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.domain.UserAccount;
import com.mobilebankingapi.feature.account.dto.AccountCreateRequest;
import com.mobilebankingapi.feature.account.dto.AccountResponse;
import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;
import com.mobilebankingapi.feature.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AccountTypeMapper.class
})
public interface AccountMapper {

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    @Mapping(source = "userAccountList", target = "userResponse", qualifiedByName = "mapUserResponse")
    AccountResponse toAccountResponse(Account account);

}
