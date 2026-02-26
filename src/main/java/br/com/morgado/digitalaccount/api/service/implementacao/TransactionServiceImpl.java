package br.com.morgado.digitalaccount.api.service.implementacao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;
import br.com.morgado.digitalaccount.api.dto.request.TransactionRequest;
import br.com.morgado.digitalaccount.api.exception.DatabaseException;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.TransactionRepository;
import br.com.morgado.digitalaccount.api.service.TransactionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

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
    public Long depositRequest(Long idAccount, TransactionRequest transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'depositRequest'");
    }

    @Override
    public Long withdrawRequest(Long idAccount, TransactionRequest transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdrawRequest'");
    }

    @Override
    public Long transferRequest(String destinationAccount, TransactionRequest transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferRequest'");
    }

}
