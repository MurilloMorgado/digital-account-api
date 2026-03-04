package br.com.morgado.digitalaccount.api.AccountTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.exception.BusinessRuleException;

@DataJpaTest
@ActiveProfiles("test")
public class AccountModelTest {

    @Test
    void shouldDepositSuccessfully() {

        AccountModel account = new AccountModel();
        account.setBalance(BigDecimal.valueOf(100));

        account.deposit(BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(150), account.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenDepositIsZeroOrNegative() {

        AccountModel account = new AccountModel();
        account.setBalance(BigDecimal.valueOf(100));

        assertThrows(BusinessRuleException.class,
                () -> account.deposit(BigDecimal.ZERO));
    }

    @Test
    void shouldThrowExceptionWhenInsufficientFunds() {

        AccountModel account = new AccountModel();
        account.setBalance(BigDecimal.valueOf(100));

        assertThrows(BusinessRuleException.class,
                () -> account.withdraw(BigDecimal.valueOf(200)));
    }

}
