package br.com.morgado.digitalaccount.api.AccountTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

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
}
