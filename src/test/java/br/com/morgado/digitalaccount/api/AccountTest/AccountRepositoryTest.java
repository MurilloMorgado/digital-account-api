package br.com.morgado.digitalaccount.api.AccountTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;

@DataJpaTest
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    void shouldFindAccountByCustomer() {

        AccountModel account = new AccountModel();
        account.setCustomer("12345");
        account.setBalance(BigDecimal.valueOf(100));
        repository.save(account);

        AccountModel result = repository.findByCustomer("12345")
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        assertEquals("12345", result.getCustomer());
    }

    @Test
    void shouldReturnEmptyWhenCustomerDoesNotExist() {

        Optional<AccountModel> result = repository.findByCustomer("99999");

        assertFalse(result.isPresent());
    }

    @Test
    void shouldSaveAccount() {

        AccountModel account = new AccountModel();
        account.setCustomer("Murillo");
        account.setBalance(BigDecimal.valueOf(500));

        AccountModel saved = repository.save(account);

        assertEquals("Murillo", saved.getCustomer());
        assertEquals(BigDecimal.valueOf(500), saved.getBalance());
    }

    @Test
    void shouldDeleteAccount() {

        AccountModel account = new AccountModel();
        account.setCustomer("DeleteTest");
        account.setBalance(BigDecimal.valueOf(200));

        AccountModel saved = repository.save(account);

        repository.deleteById(saved.getId());

        Optional<AccountModel> result = repository.findById(saved.getId());

        assertFalse(result.isPresent());
    }

    @Test
    void shouldFindAccountById() {

        AccountModel account = new AccountModel();
        account.setCustomer("FindById");
        account.setBalance(BigDecimal.valueOf(300));

        AccountModel saved = repository.save(account);

        Optional<AccountModel> result = repository.findById(saved.getId());

        assertEquals(true, result.isPresent());
        assertEquals("FindById", result.get().getCustomer());
    }
}
