package br.com.morgado.digitalaccount.api;

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
import br.com.morgado.digitalaccount.api.dto.response.AccountResponse;
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
}
