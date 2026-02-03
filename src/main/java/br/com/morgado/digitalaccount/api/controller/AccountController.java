package br.com.morgado.digitalaccount.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.service.AccountService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bank-accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountModel>> findAllAccounts() {
        List<AccountModel> accounts = accountService.findAllAccounts();
        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping("/{idAccount}/account")
    public ResponseEntity<AccountModel> findAccountById(@PathVariable Long idAccount) {
        AccountModel account = accountService.findAccountById(idAccount);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping
    public ResponseEntity<Long> createAccount() {
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{idAccount}/account")
    public ResponseEntity<Void> updateAccountDetails() {
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Void> deleteAccount() {
        return ResponseEntity.ok().body(null);
    }
}
