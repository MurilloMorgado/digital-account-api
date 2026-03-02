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

        AccountModel account = findValidatedAccount(transaction.getDestinationAccount().getCurrentAccount());

        account.deposit(transaction.getAmount());

        TransactionModel tx = TransactionModel.deposit(account, transaction.getAmount());

        transactionRepository.save(tx);
        return tx.getId();
    }

    @Override
    @Transactional
    public Long withdrawRequest(Long idAccount, TransactionRequest transaction) {

        AccountModel account = findValidatedAccount(transaction.getSourceAccount().getCurrentAccount());

        account.withdraw(transaction.getAmount());

        TransactionModel tx = TransactionModel.withdraw(account, transaction.getAmount());

        transactionRepository.save(tx);
        return tx.getId();
    }

    @Override
    @Transactional
    public Long transferRequest(Long idAccount, TransactionRequest request) {

        AccountModel origin = findValidatedAccount(request.getSourceAccount().getCurrentAccount());

        AccountModel destination = findValidatedAccount(request.getDestinationAccount().getCurrentAccount());

        origin.withdraw(request.getAmount());
        destination.deposit(request.getAmount());

        TransactionModel transaction = TransactionModel.transfer(origin, destination, request.getAmount());

        accountRepository.save(origin);
        accountRepository.save(destination);
        transactionRepository.save(transaction);

        return transaction.getId();
    }

    private AccountModel findValidatedAccount(Long idAccount) {

        AccountModel account = accountRepository
                .findByCurrentAccountAndStatus(idAccount, "ACTIVE")
                .orElseThrow(() -> new ResourceNotFoundException("Account " + idAccount + " not validated or not found."));

        return account;
    }
}
