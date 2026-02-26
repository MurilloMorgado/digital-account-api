package br.com.morgado.digitalaccount.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;
import br.com.morgado.digitalaccount.api.dto.request.TransactionRequest;
import br.com.morgado.digitalaccount.api.service.TransactionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionModel>> findAllTransactions() {
        List<TransactionModel> list = transactionService.findAllTransactions();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{idTransaction}")
    public ResponseEntity<TransactionModel> findTransactionById(@PathVariable Long idTransaction) {
        TransactionModel Transaction = transactionService.findTransactionById(idTransaction);
        return ResponseEntity.ok().body(Transaction);
    }

    @PostMapping("/{idAccount}/deposit")
    public ResponseEntity<Long> depositRequest(@PathVariable Long idAccount, @RequestBody TransactionRequest transactionRequest) {
        Long idTransaction = transactionService.depositRequest(idAccount, transactionRequest);
        return ResponseEntity.ok().body(idTransaction);
    }

    @PostMapping("/{idAccount}/withdraw")
    public ResponseEntity<Long> withdrawRequest(@PathVariable Long idAccount, @RequestBody TransactionRequest transactionRequest) {
        Long idTransaction = transactionService.withdrawRequest(idAccount, transactionRequest);
        return ResponseEntity.ok().body(idTransaction);
    }

    @PostMapping("/{destinationAccount}/transfer")
    public ResponseEntity<Long> transferRequest(@PathVariable String destinationAccount,
            @RequestBody TransactionRequest transactionRequest) {
        Long idTransaction = transactionService.transferRequest(destinationAccount, transactionRequest);
        return ResponseEntity.ok().body(idTransaction);
    }

}
