package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.feature.account.dto.AccountCreateRequest;
import com.mobilebankingapi.feature.account.dto.AccountRenameRequest;
import com.mobilebankingapi.feature.account.dto.AccountResponse;
import com.mobilebankingapi.feature.account.dto.AccountSetTransferRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    Page<AccountResponse> findList(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return accountService.findList(page, size);
    }

    @PutMapping("/{actNo}/hide")
    void hiddenAccountByActNo(@PathVariable String actNo){
        accountService.hiddenAccount(actNo);
    }
    @PutMapping("/{actNo}/rename")
    AccountResponse renameByActNo(@PathVariable String actNo , @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameByActNo(actNo,accountRenameRequest);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }

    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findAccountByAccountNumber(actNo);
    }

    @PutMapping("/{actNo}/transfer-account")
    AccountResponse setAccountLimitTransfer(@PathVariable String actNo,
                                            @RequestBody AccountSetTransferRequest accountSetTransferRequest) {

        return accountService.setAccountLimitTransfer(actNo,accountSetTransferRequest);
    }

}
