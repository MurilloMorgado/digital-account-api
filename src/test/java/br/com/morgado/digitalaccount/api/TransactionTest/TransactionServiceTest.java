package br.com.morgado.digitalaccount.api.TransactionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;
import br.com.morgado.digitalaccount.api.dto.request.AccountRequest;
import br.com.morgado.digitalaccount.api.dto.request.TransactionRequest;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;
import br.com.morgado.digitalaccount.api.repository.TransactionRepository;
import br.com.morgado.digitalaccount.api.service.implementacao.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

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

    @Test
    void shouldFindTransactionById() {

        TransactionModel transaction = new TransactionModel();
        transaction.setId(1L);
        transaction.setAmount(BigDecimal.valueOf(500));

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        TransactionModel result = transactionServiceImpl.findTransactionById(1L);

        assertEquals(1L, result.getId());
        assertEquals(BigDecimal.valueOf(500), result.getAmount());
    }

    @Test
    void shouldThrowResourceNotFoundWhenTransactionIdDoesNotExist() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> transactionServiceImpl.findTransactionById(1L));
    }

    @Test
    void shouldDepositSuccessfully() {

        AccountRequest destinationRequest = new AccountRequest();
        destinationRequest.setCurrentAccount(1001L);
        destinationRequest.setAgency(123L);
        destinationRequest.setCustomer("Murillo");
        destinationRequest.setBank("BankTest");
        destinationRequest.setAccountType("CHECKING");

        TransactionRequest request = new TransactionRequest();
        request.setDestinationAccount(destinationRequest);
        request.setAmount(BigDecimal.valueOf(500));

        AccountModel account = new AccountModel();
        account.setCurrentAccount(1001L);
        account.setBalance(BigDecimal.valueOf(1000));

        when(accountRepository.findByCurrentAccountAndStatus(1001L, "ACTIVE"))
                .thenReturn(Optional.of(account));

        when(transactionRepository.save(any(TransactionModel.class))).thenAnswer(i -> {
            TransactionModel t = i.getArgument(0);
            t.setId(1L);
            return t;
        });

        Long txId = transactionServiceImpl.depositRequest(request);

        assertEquals(1L, txId);
        assertEquals(BigDecimal.valueOf(1500), account.getBalance());
    }

    @Test
    void shouldWithdrawSuccessfully() {

        AccountRequest sourceRequest = new AccountRequest();
        sourceRequest.setCurrentAccount(2001L);
        sourceRequest.setAgency(123L);
        sourceRequest.setCustomer("Murillo");
        sourceRequest.setBank("BankTest");
        sourceRequest.setAccountType("CHECKING");

        TransactionRequest request = new TransactionRequest();
        request.setSourceAccount(sourceRequest);
        request.setAmount(BigDecimal.valueOf(400));

        AccountModel account = new AccountModel();
        account.setCurrentAccount(2001L);
        account.setBalance(BigDecimal.valueOf(1000));

        when(accountRepository.findByCurrentAccountAndStatus(2001L, "ACTIVE"))
                .thenReturn(Optional.of(account));

        when(transactionRepository.save(any(TransactionModel.class))).thenAnswer(i -> {
            TransactionModel t = i.getArgument(0);
            t.setId(2L);
            return t;
        });

        Long txId = transactionServiceImpl.withdrawRequest(request);

        assertEquals(2L, txId);
        assertEquals(BigDecimal.valueOf(600), account.getBalance());
    }

    @Test
    void shouldTransferSuccessfully() {

        AccountRequest sourceRequest = new AccountRequest();
        sourceRequest.setCurrentAccount(3001L);
        sourceRequest.setAgency(123L);
        sourceRequest.setCustomer("Murillo");
        sourceRequest.setBank("BankTest");
        sourceRequest.setAccountType("CHECKING");

        AccountRequest destinationRequest = new AccountRequest();
        destinationRequest.setCurrentAccount(4001L);
        destinationRequest.setAgency(321L);
        destinationRequest.setCustomer("Alice");
        destinationRequest.setBank("BankTest");
        destinationRequest.setAccountType("CHECKING");

        TransactionRequest request = new TransactionRequest();
        request.setSourceAccount(sourceRequest);
        request.setDestinationAccount(destinationRequest);
        request.setAmount(BigDecimal.valueOf(300));

        AccountModel origin = new AccountModel();
        origin.setCurrentAccount(3001L);
        origin.setBalance(BigDecimal.valueOf(1000));

        AccountModel destination = new AccountModel();
        destination.setCurrentAccount(4001L);
        destination.setBalance(BigDecimal.valueOf(500));

        when(accountRepository.findByCurrentAccountAndStatus(3001L, "ACTIVE")).thenReturn(Optional.of(origin));
        when(accountRepository.findByCurrentAccountAndStatus(4001L, "ACTIVE")).thenReturn(Optional.of(destination));

        when(transactionRepository.save(any(TransactionModel.class))).thenAnswer(i -> {
            TransactionModel t = i.getArgument(0);
            t.setId(3L);
            return t;
        });

        Long txId = transactionServiceImpl.transferRequest(request);

        assertEquals(3L, txId);
        assertEquals(BigDecimal.valueOf(700), origin.getBalance());
        assertEquals(BigDecimal.valueOf(800), destination.getBalance());
    }

    @Test
    void shouldThrowResourceNotFoundWhenAccountNotFoundForDeposit() {

        AccountRequest destinationRequest = new AccountRequest();
        destinationRequest.setCurrentAccount(5001L);
        destinationRequest.setAgency(123L);
        destinationRequest.setCustomer("Murillo");
        destinationRequest.setBank("BankTest");
        destinationRequest.setAccountType("CHECKING");

        TransactionRequest request = new TransactionRequest();
        request.setDestinationAccount(destinationRequest);
        request.setAmount(BigDecimal.valueOf(100));

        when(accountRepository.findByCurrentAccountAndStatus(5001L, "ACTIVE"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> transactionServiceImpl.depositRequest(request));
    }
}
