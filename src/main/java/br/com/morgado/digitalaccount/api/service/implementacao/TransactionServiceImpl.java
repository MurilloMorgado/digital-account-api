package br.com.morgado.digitalaccount.api.service.implementacao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;
import br.com.morgado.digitalaccount.api.dto.request.TransactionRequest;
import br.com.morgado.digitalaccount.api.exception.DatabaseException;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;
import br.com.morgado.digitalaccount.api.repository.TransactionRepository;
import br.com.morgado.digitalaccount.api.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<TransactionModel> findAllTransactions() {
        try {

            List<TransactionModel> transactions = transactionRepository.findAll();

            if (transactions.isEmpty()) {
                throw new ResourceNotFoundException("No accounts found");
            }

            return transactions;
        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }
    }

    @Override
    public TransactionModel findTransactionById(Long idTransaction) {
        try {

            TransactionModel transaction = transactionRepository.findById(idTransaction)
                    .orElseThrow(() -> new ResourceNotFoundException("No transaction found by ID: " + idTransaction));

            return transaction;

        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }
    }

    @Override
    @Transactional
    public Long depositRequest(Long idAccount, TransactionRequest transaction) {

        AccountModel account = accountRepository.findById(idAccount)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.deposit(transaction.getAmount());

        TransactionModel tx = TransactionModel.deposit(account, transaction.getAmount());

        transactionRepository.save(tx);
        return tx.getId();
    }

    @Override
    @Transactional
    public Long withdrawRequest(Long idAccount, TransactionRequest transaction) {

        AccountModel account = accountRepository.findById(idAccount)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.withdraw(transaction.getAmount());

        TransactionModel tx = TransactionModel.withdraw(account, transaction.getAmount());

        transactionRepository.save(tx);
        return tx.getId();
    }

    @Override
    @Transactional
    public Long transferRequest(Long idAccount, TransactionRequest request) {

        AccountModel origin = accountRepository.findByCurrentAccount(request.getSourceAccount().getCurrentAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        AccountModel destination = accountRepository.findByCurrentAccount(request.getDestinationAccount().getCurrentAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        origin.withdraw(request.getAmount());
        destination.deposit(request.getAmount());

        TransactionModel transaction = TransactionModel.transfer(origin, destination, request.getAmount());

        accountRepository.save(origin);
        accountRepository.save(destination);
        transactionRepository.save(transaction);

        return transaction.getId();
    }

}
