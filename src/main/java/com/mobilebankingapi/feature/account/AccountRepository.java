package com.mobilebankingapi.feature.account;

import com.mobilebankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountByActNo(String ActNumber);
    boolean existsByActNo(String actNo);

    @Modifying
    @Query("""
    UPDATE Account As a
    SET a.isHidden= TRUE
    WHERE a.actNo = ?1""")
    void hiddenAccountByActNo(String actNo);
}
