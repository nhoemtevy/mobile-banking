package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.domain.Account;
import com.mobilebankingapi.domain.AccountType;
import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.domain.UserAccount;
import com.mobilebankingapi.feature.account.dto.AccountCreateRequest;
import com.mobilebankingapi.feature.account.dto.AccountRenameRequest;
import com.mobilebankingapi.feature.account.dto.AccountResponse;
import com.mobilebankingapi.feature.account.dto.AccountSetTransferRequest;
import com.mobilebankingapi.feature.accounttype.AccountTypeRepository;
import com.mobilebankingapi.feature.user.UserRepository;
import com.mobilebankingapi.util.RandomUtil;
import com.mobilebankingapi.mapper.AccountMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        account.setActNo(RandomUtil.generate9Digits());
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
        Account account = accountRepository.findAccountByActNo(accountNumber).orElseThrow();
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest) {
        Account account = accountRepository.findAccountByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Account has not been fond"));

        if (account.getAlias() != null && account.getAlias()
                .equals(accountRenameRequest.newName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "New name must not the same as old name");
        }

        account.setAlias(accountRenameRequest.newName());
        account = accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }


    @Transactional
    @Override
    public void hiddenAccount(String actNo) {
        if (!accountRepository.existsByActNo(actNo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account has not been found!");
        }
        try{
            accountRepository.hiddenAccountByActNo(actNo);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something when wrong");
        }
    }

    @Override
    public Page<AccountResponse> findList(int page, int size) {
        if (page < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number be greater than or equal to zero");
        }

        if (size < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }
        Sort sortByActName = Sort.by(Sort.Direction.ASC,"actName");

        PageRequest pageRequest = PageRequest.of(page, size, sortByActName);

        Page<Account> accounts = accountRepository.findAll(pageRequest);

        return accounts.map(accountMapper::toAccountResponse);
    }


    @Override
    public AccountResponse setAccountLimitTransfer(String actNo, AccountSetTransferRequest accountSetTransferRequest) {
        Account account = accountRepository.findAccountByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account has not been fond"));


        account.setTransferLimit(accountSetTransferRequest.transferLimit());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }


}
