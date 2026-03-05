package br.com.morgado.digitalaccount.api.AccountTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
import br.com.morgado.digitalaccount.api.dto.request.AccountRequest;
import br.com.morgado.digitalaccount.api.dto.response.AccountResponse;
import br.com.morgado.digitalaccount.api.exception.AlreadyExistsException;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;
import br.com.morgado.digitalaccount.api.service.implementacao.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void shouldReturnAllAccounts() {

        AccountModel account = new AccountModel();
        account.setAgency(1234L);
        account.setCurrentAccount(5678L);
        account.setCustomer("Murillo");
        account.setBalance(BigDecimal.valueOf(1000));

        when(accountRepository.findAll()).thenReturn(List.of(account));

        List<AccountResponse> result = accountService.findAllAccounts();

        assertEquals(1, result.size());
        assertEquals("Murillo", result.get(0).getCustomer());
    }

    @Test
    void shouldThrowResourceNotFoundWhenEmpty() {

        when(accountRepository.findAll()).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class,
                () -> accountService.findAllAccounts());
    }

    @Test
    void shouldReturnAccountById() {

        AccountModel account = new AccountModel();
        account.setId(1L);
        account.setCustomer("Murillo");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountResponse response = accountService.findAccountById(1L);

        assertEquals("Murillo", response.getCustomer());
    }

    @Test
    void shouldThrowResourceNotFoundWhenAccountIdDoesNotExist() {

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> accountService.findAccountById(1L));
    }

    @Test
    void shouldCreateAccountSuccessfully() {

        AccountRequest request = new AccountRequest();
        request.setCustomer("Murillo");

        AccountModel savedAccount = new AccountModel();
        savedAccount.setId(1L);
        savedAccount.setCustomer("Murillo");

        when(accountRepository.findByCustomer("Murillo"))
                .thenReturn(Optional.empty());

        when(accountRepository.save(any(AccountModel.class)))
                .thenReturn(savedAccount);

        Long id = accountService.createAccount(request);

        assertEquals(1L, id);
    }

    @Test
    void shouldThrowAlreadyExistsExceptionWhenCustomerAlreadyHasAccount() {

        AccountRequest request = new AccountRequest();
        request.setCustomer("Murillo");

        AccountModel existingAccount = new AccountModel();
        existingAccount.setCustomer("Murillo");

        when(accountRepository.findByCustomer("Murillo"))
                .thenReturn(Optional.of(existingAccount));

        assertThrows(AlreadyExistsException.class,
                () -> accountService.createAccount(request));
    }

    @Test
    void shouldUpdateAccountDetails() {

        AccountRequest request = new AccountRequest();
        request.setCustomer("Murillo Updated");

        AccountModel account = new AccountModel();
        account.setId(1L);
        account.setCustomer("Murillo");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(AccountModel.class))).thenReturn(account);

        accountService.updateAccountDetails(1L, request);

        assertEquals("Murillo Updated", account.getCustomer());
    }

    @Test
    void shouldThrowResourceNotFoundWhenUpdatingNonExistingAccount() {

        AccountRequest request = new AccountRequest();
        request.setCustomer("Murillo");

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> accountService.updateAccountDetails(1L, request));
    }

    @Test
    void shouldDeleteAccount() {

        AccountModel account = new AccountModel();
        account.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).deleteById(1L);

        accountService.deleteAccount(1L);
    }

    @Test
    void shouldThrowResourceNotFoundWhenDeletingNonExistingAccount() {

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> accountService.deleteAccount(1L));
    }
}
