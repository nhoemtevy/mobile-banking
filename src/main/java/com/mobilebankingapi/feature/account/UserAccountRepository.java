package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository  extends JpaRepository<UserAccount, Long> {
}
