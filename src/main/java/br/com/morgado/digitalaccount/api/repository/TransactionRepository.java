package br.com.morgado.digitalaccount.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.morgado.digitalaccount.api.domain.model.TransactionModel;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

}
