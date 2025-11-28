package org.formation.projet_somkhit_willy.service.impl;

import lombok.RequiredArgsConstructor;
import org.formation.projet_somkhit_willy.entity.*;
import org.formation.projet_somkhit_willy.repository.*;
import org.formation.projet_somkhit_willy.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CurrentAccountRepository currentAccountRepository;
    private final SavingAccountRepository savingAccountRepository;
    private final ClientRepository clientRepository;

    @Override
    public CurrentAccount createCurrentAccount(long clientId, UUID accountNumber, BigDecimal initialBalance) {
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
    public SavingAccount createSavingAccount(long clientId, UUID accountNumber, BigDecimal initialBalance) {
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
    public Account getAccountByNumber(UUID accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public BigDecimal getAvailableBalance(UUID accountNumber) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        BigDecimal availableBalance = account.getBalance();
        if (account instanceof CurrentAccount) {
            CurrentAccount current = (CurrentAccount) account;
            availableBalance = availableBalance.add(BigDecimal.valueOf(current.getOverdraftLimit()));
        }
        return availableBalance;
    }

    @Override
    public Account creditAccount(UUID accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    @Override
    public Account debitAccount(UUID accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        BigDecimal availableBalance = getAvailableBalance(accountNumber);
        if(availableBalance.compareTo(amount) < 0)
        {
            throw new RuntimeException("Insufficient funds\navailable balance: "+availableBalance);
        }
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account applyInterest(UUID accountNumber) {
        Account account = getAccountByNumber(accountNumber);

        if (!(account instanceof SavingAccount)) {
            throw new RuntimeException("Account is not a SavingAccount");
        }

        SavingAccount savingAccount = (SavingAccount) account;

        BigDecimal currentBalance = savingAccount.getBalance();
        BigDecimal interest = currentBalance.multiply(BigDecimal.valueOf(savingAccount.getInterestRate()));
        savingAccount.setBalance(currentBalance.add(interest));

        accountRepository.save(savingAccount);

        return savingAccount;
    }

    @Transactional
    public void transfer(UUID fromAccount, UUID toAccount, BigDecimal amount) {
        Account source = accountRepository.findById(fromAccount)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account target = accountRepository.findById(toAccount)
                .orElseThrow(() -> new RuntimeException("Target account not found"));


        BigDecimal availableBalance = getAvailableBalance(fromAccount);

        if (source.getBalance().compareTo(availableBalance) < 0) {
            throw new RuntimeException("Insufficient funds\navailable balance: "+availableBalance);
        }

        source.setBalance(source.getBalance().subtract(amount));
        target.setBalance(target.getBalance().add(amount));

        accountRepository.save(source);
        accountRepository.save(target);
    }
}
