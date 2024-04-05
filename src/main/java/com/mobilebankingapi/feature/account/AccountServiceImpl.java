package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.domain.Account;
import com.mobilebankingapi.domain.AccountType;
import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.domain.UserAccount;
import com.mobilebankingapi.feature.account.dto.AccountCreateRequest;
import com.mobilebankingapi.feature.account.dto.AccountResponse;
import com.mobilebankingapi.feature.accounttype.AccountTypeRepository;
import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;
import com.mobilebankingapi.feature.user.UserRepository;
import com.mobilebankingapi.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;


    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {

        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Invalid account type"));

        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found"));

        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setActNo("123456789");
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreatedAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findAccountByActNo(accountNumber);
        return accountMapper.toAccountResponse(account);
    }



}
