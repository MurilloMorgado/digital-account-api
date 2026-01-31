package br.com.morgado.digitalaccount.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bank-accounts")
@RequiredArgsConstructor
public class AccountController {

    @GetMapping
    public ResponseEntity<List<AccountModel>> findAllAccounts() {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping
    public ResponseEntity<AccountModel> findAccountById() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping
    public ResponseEntity<Long> createAccount() {
        return ResponseEntity.ok().body(null);
    }

    @PutMapping
    public ResponseEntity<Void> updateAccountDetails() {
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount() {
        return ResponseEntity.ok().body(null);
    }
}
