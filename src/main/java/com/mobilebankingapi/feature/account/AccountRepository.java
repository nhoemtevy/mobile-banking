package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountByActNo(String ActNumber);
}
