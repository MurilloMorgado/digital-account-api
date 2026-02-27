package br.com.morgado.digitalaccount.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACTION")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SOURCE_ACCOUNT_ID")
    private AccountModel sourceAccount;

    @ManyToOne
    @JoinColumn(name = "DESTINATION_ACCOUNT_ID")
    private AccountModel destinationAccount;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "STATUS")
    private String status;

    public static TransactionModel deposit(AccountModel account, BigDecimal request) {

        TransactionModel transaction = new TransactionModel();

        transaction.setSourceAccount(null);
        transaction.setDestinationAccount(account);
        transaction.setType("DEPOSIT");
        transaction.setAmount(request);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus("COMPLETED");

        return transaction;

    }

    public static TransactionModel withdraw(AccountModel account, BigDecimal request) {

        TransactionModel transaction = new TransactionModel();

        transaction.setSourceAccount(account);
        transaction.setDestinationAccount(null);
        transaction.setType("WITHDRAW");
        transaction.setAmount(request);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus("COMPLETED");

        return transaction;

    }

    public static TransactionModel transfer(AccountModel origin, AccountModel destination, BigDecimal request) {

        TransactionModel transaction = new TransactionModel();

        transaction.setSourceAccount(origin);
        transaction.setDestinationAccount(destination);
        transaction.setType("TRANSFER");
        transaction.setAmount(request);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus("COMPLETED");

        return transaction;

    }
}
