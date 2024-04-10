package com.mobilebankingapi.feature.transaction;

import com.mobilebankingapi.domain.Account;
import com.mobilebankingapi.domain.Transaction;
import com.mobilebankingapi.feature.account.AccountRepository;
import com.mobilebankingapi.feature.transaction.dto.TransactionCreateRequest;
import com.mobilebankingapi.feature.transaction.dto.TransactionResponse;
import com.mobilebankingapi.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {

        log.info("transfer(TransactionCreateRequest transactionCreateRequest): {}", transactionCreateRequest);

        //Validate owner account no
        Account owner = accountRepository.findAccountByActNo(transactionCreateRequest.ownerActNo())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account transfer receiver has not been found"
                ));

        Account accountReceiver = accountRepository.findAccountByActNo(transactionCreateRequest.transferReceiverActNo())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account transfer receiver has not been found"
                ));

        //Check amount transfer (balance < amount)
        if (owner.getBalance().doubleValue() < transactionCreateRequest.amount().doubleValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insufficient balance");
        }

        //check amount transfer with transfer limit
        if (transactionCreateRequest.amount().doubleValue() >= owner.getTransferLimit().doubleValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transaction has been over the transfer limit");
        }

        //ដកលុយពី account
        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));

        //បញ្ចូលលុយទៅ account

        accountReceiver.setBalance(accountReceiver.getBalance()
                .add(transactionCreateRequest.amount()));

        Transaction transaction = transactionMapper.fromTransactionCreateRequest(transactionCreateRequest);
        transaction.setOwner(owner);

        transaction.setTransferReceiver(accountReceiver);
        transaction.setTransactionType("TRANSFER");

        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setStatus(true);
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> findAll(int page, int size, String sortDirection) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero");
        }

        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }
        Sort.Direction direction = Sort.Direction.DESC;
        if(sortDirection.equalsIgnoreCase("ASC")){
            direction = Sort.Direction.ASC;
        }
        Sort sortByTransactionAt = Sort.by(direction, "transactionAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortByTransactionAt );
        Page<Transaction> transactions = transactionRepository.findAll(pageRequest);

        return transactions.map(transactionMapper::toTransactionResponse);
    }



}
