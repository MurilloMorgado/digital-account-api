package br.com.morgado.digitalaccount.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.morgado.digitalaccount.api.domain.model.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmailIgnoreCaseAndVerifiedEmailTrue(String email);

    Optional<UserModel> findByToken(String token);
    
}