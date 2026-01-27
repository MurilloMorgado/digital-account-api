package br.com.morgado.digitalaccount.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.morgado.digitalaccount.api.domain.model.UserModel;
import br.com.morgado.digitalaccount.api.repository.UserRepository;

@SpringBootApplication
public class DigitalAccountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalAccountApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		return args -> {
			UserModel user = new UserModel();
			user.setEmail("murillo@hotmail.com");
			user.setFullName("Murillo");
			user.setPassword(passwordEncoder.encode("Maria123"));
			user.setUserName("Mmorgado");

			userRepository.save(user);
		};
	}

}
