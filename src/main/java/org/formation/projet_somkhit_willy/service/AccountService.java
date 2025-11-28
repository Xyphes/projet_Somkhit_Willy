package org.formation.projet_somkhit_willy.service;

import org.formation.projet_somkhit_willy.entity.*;

import java.math.BigDecimal;

public interface AccountService {
    CurrentAccount createCurrentAccount(long clientId, long accountNumber, BigDecimal initialBalance);
    SavingAccount createSavingAccount(long clientId, long accountNumber, BigDecimal initialBalance);
    Account getAccountByNumber(long accountNumber);
    Account creditAccount(long accountNumber, BigDecimal amount);
    Account debitAccount(long accountNumber, BigDecimal amount);
}
