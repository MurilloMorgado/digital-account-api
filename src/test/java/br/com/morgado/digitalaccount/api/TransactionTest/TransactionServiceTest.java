package br.com.morgado.digitalaccount.api.TransactionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.TransactionRepository;
import br.com.morgado.digitalaccount.api.service.implementacao.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;

    @Test
    void shouldReturnAlltransactions() {

        AccountModel account = new AccountModel();
        account.setCurrentAccount(5678L);

        TransactionModel transaction = new TransactionModel();
        transaction.setAmount(BigDecimal.valueOf(1000));
        transaction.setSourceAccount(null);
        transaction.setDestinationAccount(account);
        transaction.setStatus("ACTIVE");
        transaction.setType("CHECKING");

        when(transactionRepository.findAll()).thenReturn(List.of(transaction));

        List<TransactionModel> result = transactionServiceImpl.findAllTransactions();

        assertEquals(1, result.size());
        TransactionModel returned = result.get(0);

        assertEquals(BigDecimal.valueOf(1000), returned.getAmount());
        assertEquals("ACTIVE", returned.getStatus());
        assertEquals("CHECKING", returned.getType());

    }

    @Test
    void shouldThrowResourceNotFoundWhenEmpty() {
        when(transactionRepository.findAll()).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class,
                () -> transactionServiceImpl.findAllTransactions());
    }
}
