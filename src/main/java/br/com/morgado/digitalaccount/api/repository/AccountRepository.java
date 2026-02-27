package br.com.morgado.digitalaccount.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import java.util.Optional;



public interface AccountRepository extends JpaRepository<AccountModel, Long>{
    
    Optional<AccountModel> findByCustomer(String customer);

    Optional<AccountModel> findByCurrentAccount(Long currentAccount);
}
