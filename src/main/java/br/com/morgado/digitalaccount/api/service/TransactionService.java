package br.com.morgado.digitalaccount.api.service;

import java.util.List;

import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;
import br.com.morgado.digitalaccount.api.dto.request.TransactionRequest;

public interface TransactionService {

    List<TransactionModel> findAllTransactions();

    TransactionModel findTransactionById(Long idTransaction);

    Long depositRequest(Long idAccount, TransactionRequest transaction);

    Long withdrawRequest(Long idAccount, TransactionRequest transaction);

    Long transferRequest(Long idAccount, TransactionRequest transaction);

}
