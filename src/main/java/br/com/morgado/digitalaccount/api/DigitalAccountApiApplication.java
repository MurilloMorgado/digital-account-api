package br.com.morgado.digitalaccount.api;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.domain.model.UserModel;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;
import br.com.morgado.digitalaccount.api.repository.UserRepository;

@SpringBootApplication
public class DigitalAccountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalAccountApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AccountRepository accountRepository) {

		return args -> {

			// Criação do usuário
			UserModel user = new UserModel();
			user.setEmail("murillo@hotmail.com");
			user.setFullName("Murillo");
			user.setPassword(passwordEncoder.encode("Morgado123"));
			user.setUserName("Mmorgado");
			user.setVerifiedEmail(true);

			userRepository.save(user);

			// Criação da conta
			AccountModel account = new AccountModel();
			account.setAgency(1234L);
			account.setCurrentAccount(567890L);
			account.setCustomer(user.getFullName());
			account.setBalance(BigDecimal.ZERO);
			account.setBank("Digital Bank");
			account.setAccountType("CHECKING");
			account.setStatus("ACTIVE");
			account.setOverdraftLimit(new BigDecimal("1000.00"));
			account.setOpeningDate(new Date());

			accountRepository.save(account);
		};
	}

}
