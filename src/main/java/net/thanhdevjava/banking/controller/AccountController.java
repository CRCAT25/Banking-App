package net.thanhdevjava.banking.controller;

import net.thanhdevjava.banking.dto.AccountDTO;
import net.thanhdevjava.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    // Get Account By account id REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> requestBody) {

        Double amount = requestBody.get("amount");
        AccountDTO accountDTO = accountService.deposit(id, amount);

        return ResponseEntity.ok(accountDTO);
    }

    // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id,
                                              @RequestBody Map<String, Double> requestBody) {

        Double amount = requestBody.get("amount");
        AccountDTO accountDTO = accountService.withdraw(id, amount);

        return ResponseEntity.ok(accountDTO);
    }

    // Get all accounts REST API
    @GetMapping
    public ResponseEntity<Iterable<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // Delete account by id REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }
}
