package com.mobilebankingapi.feature.accounttype;

import com.mobilebankingapi.feature.accounttype.dto.AccountTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AccountTypeResponse> findList() {
        return accountTypeService.findList();
    }

    @GetMapping("/{alias}")
    AccountTypeResponse findByAlias(@PathVariable String alias) {
        return accountTypeService.findByAlias(alias);
    }

}
