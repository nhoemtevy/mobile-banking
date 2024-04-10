package com.mobilebankingapi.feature.transaction;

import com.mobilebankingapi.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
