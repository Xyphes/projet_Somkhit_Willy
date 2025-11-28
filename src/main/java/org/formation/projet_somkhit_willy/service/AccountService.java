package org.formation.projet_somkhit_willy.service;

import org.formation.projet_somkhit_willy.entity.*;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    CurrentAccount createCurrentAccount(long clientId, UUID accountNumber, BigDecimal initialBalance);
    SavingAccount createSavingAccount(long clientId, UUID accountNumber, BigDecimal initialBalance);
    Account getAccountByNumber(UUID accountNumber);
    Account creditAccount(UUID accountNumber, BigDecimal amount);
    Account debitAccount(UUID accountNumber, BigDecimal amount);
    BigDecimal getAvailableBalance(UUID accountNumber);

    void transfer(UUID fromAccount, UUID toAccount, BigDecimal amount);
}
