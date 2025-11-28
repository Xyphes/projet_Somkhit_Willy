package org.formation.projet_somkhit_willy.controller;

import org.formation.projet_somkhit_willy.entity.*;
import org.formation.projet_somkhit_willy.repository.*;
import org.formation.projet_somkhit_willy.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final SavingAccountRepository savingAccountRepository;
    private final CurrentAccountRepository currentAccountRepository;

    public AccountController(
            AccountService accountService,
            AccountRepository accountRepository,
            ClientRepository clientRepository,
            SavingAccountRepository savingAccountRepository,
            CurrentAccountRepository currentAccountRepository
    ) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.savingAccountRepository = savingAccountRepository;
        this.currentAccountRepository = currentAccountRepository;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    @PostMapping("/saving/{clientId}")
    public ResponseEntity<?> createSavingAccount(@PathVariable("clientId") Long clientId) {
        // Fetch client
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Check in the database if the client already has a saving account
        if ((client.getSavingAccount() != null)) {
            return ResponseEntity.badRequest()
                    .body("Client already has a saving account");
        }

        // Create new saving account
        SavingAccount account = new SavingAccount();
        account.setClient(client);
        account.setBalance(BigDecimal.ZERO);
        account.setOpeningDate(LocalDate.now());
        account.setInterestRate(0.03);

        // Set bidirectional link
        client.setSavingAccount(account);

        // Save the account
        SavingAccount savedAccount = savingAccountRepository.save(account);

        return ResponseEntity.ok(savedAccount);
    }



    // --- CREATE CURRENT ACCOUNT ---
    @PostMapping("/current/{clientId}")
    public ResponseEntity<?> createCurrentAccount(@PathVariable("clientId") Long clientId) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));


        if (client.getCurrentAccount() != null) {
            return ResponseEntity.badRequest()
                    .body("Client already has a current account");
        }

        CurrentAccount account = new CurrentAccount();
        account.setClient(client);
        account.setBalance(BigDecimal.ZERO);

        currentAccountRepository.save(account);
        client.setCurrentAccount(account);
        clientRepository.save(client);

        return ResponseEntity.ok(account);
    }


    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }

    @PostMapping("/{accountNumber}/credit")
    public ResponseEntity<Account> credit(@PathVariable UUID accountNumber, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.creditAccount(accountNumber, amount));
    }

    @PostMapping("/{accountNumber}/debit")
    public ResponseEntity<Account> debit(@PathVariable UUID accountNumber, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.debitAccount(accountNumber, amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody Map<String, Object> body) {
        try {
            UUID fromAccount = UUID.fromString((String) body.get("fromAccount"));
            UUID toAccount = UUID.fromString((String) body.get("toAccount"));
            BigDecimal amount = new BigDecimal(body.get("amount").toString());

            accountService.transfer(fromAccount, toAccount, amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request format");
        }
    }

    @GetMapping("/audit")
    public ResponseEntity<List<Account>> auditAccounts() {
        return ResponseEntity.ok(accountService.auditAccounts());
    }


}
