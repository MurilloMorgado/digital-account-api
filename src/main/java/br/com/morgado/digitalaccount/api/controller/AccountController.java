package br.com.morgado.digitalaccount.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.morgado.digitalaccount.api.dto.request.AccountRequest;
import br.com.morgado.digitalaccount.api.dto.response.AccountResponse;
import br.com.morgado.digitalaccount.api.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bank-accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        List<AccountResponse> accounts = accountService.findAllAccounts();
        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping("/{idAccount}/account")
    public ResponseEntity<AccountResponse> findAccountById(@PathVariable Long idAccount) {
        AccountResponse account = accountService.findAccountById(idAccount);
        return ResponseEntity.ok().body(account);
    }

    //TODO check when sending an empty body
    @PostMapping
    public ResponseEntity<Long> createAccount(@RequestBody @Valid AccountRequest account) {
        Long idAccount = accountService.createAccount(account);
        return ResponseEntity.ok().body(idAccount);
    }

    @PutMapping("/{idAccount}/account")
    public ResponseEntity<Void> updateAccountDetails(@PathVariable Long idAccount, @RequestBody @Valid AccountRequest account) {
        accountService.updateAccountDetails(idAccount, account);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long idAccount) {
        accountService.deleteAccount(idAccount);
        return ResponseEntity.ok().build();
    }
}
