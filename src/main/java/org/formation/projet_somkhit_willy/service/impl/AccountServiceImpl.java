package org.formation.projet_somkhit_willy.service.impl;

import lombok.RequiredArgsConstructor;
import org.formation.projet_somkhit_willy.entity.*;
import org.formation.projet_somkhit_willy.repository.*;
import org.formation.projet_somkhit_willy.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CurrentAccountRepository currentAccountRepository;
    private final SavingAccountRepository savingAccountRepository;
    private final ClientRepository clientRepository;

    @Override
    public CurrentAccount createCurrentAccount(long clientId, long accountNumber, BigDecimal initialBalance) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        CurrentAccount account = new CurrentAccount();
        account.setAccountNumber(accountNumber);
        account.setBalance(initialBalance);
        account.setOpeningDate(LocalDate.now());
        account.setClient(client);
        account.setOverdraftLimit(1000.0);

        return currentAccountRepository.save(account);
    }

    @Override
    public SavingAccount createSavingAccount(long clientId, long accountNumber, BigDecimal initialBalance) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        SavingAccount account = new SavingAccount();
        account.setAccountNumber(accountNumber);
        account.setBalance(initialBalance);
        account.setOpeningDate(LocalDate.now());
        account.setClient(client);
        account.setInterestRate(0.03);

        return savingAccountRepository.save(account);
    }

    @Override
    public Account getAccountByNumber(long accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account creditAccount(long accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    @Override
    public Account debitAccount(long accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }
}
