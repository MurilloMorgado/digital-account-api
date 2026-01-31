package br.com.morgado.digitalaccount.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;

public interface AccountRepository extends JpaRepository<AccountModel, Long>{
    
}
