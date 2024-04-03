package com.mobilebankingapi.feature.accounttype;

import com.mobilebankingapi.domain.AccountType;
import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;
import com.mobilebankingapi.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class AccountTypeServiceImpl implements AccountTypeService{

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    @Override
    public List<AccountTypeResponse> findList() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();

        return accountTypeMapper.toListAccountTypeResponse(accountTypes);

    }

    @Override
    public AccountTypeResponse findByAlias(String alias) {
        AccountType accountType = accountTypeRepository.findByAlias(alias)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Account type has not been found!"));

        return accountTypeMapper.toAccountTypeResponse(accountType);

    }
}
