package org.formation.projet_somkhit_willy.controller;

import org.formation.projet_somkhit_willy.entity.Account;
import org.formation.projet_somkhit_willy.repository.AccountRepository;
import org.formation.projet_somkhit_willy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }


    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable long accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }

    @PostMapping("/{accountNumber}/credit")
    public ResponseEntity<Account> credit(@PathVariable long accountNumber, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.creditAccount(accountNumber, amount));
    }

    @PostMapping("/{accountNumber}/debit")
    public ResponseEntity<Account> debit(@PathVariable long accountNumber, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.debitAccount(accountNumber, amount));
    }
}
