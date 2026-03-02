package br.com.morgado.digitalaccount.api.service;

import java.util.List;

import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;
import br.com.morgado.digitalaccount.api.dto.request.TransactionRequest;

public interface TransactionService {

    List<TransactionModel> findAllTransactions();

    TransactionModel findTransactionById(Long idTransaction);

    Long depositRequest(TransactionRequest transaction);

    Long withdrawRequest(TransactionRequest transaction);

    Long transferRequest(TransactionRequest transaction);

}
