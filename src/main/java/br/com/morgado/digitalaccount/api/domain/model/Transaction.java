package br.com.morgado.digitalaccount.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACTION")
    private Long id;

    @Column(name = "SOURCE_ACCOUNT")
    private String sourceAccount;

    @Column(name = "DESTINATION_ACCOUNT")
    private String destinationAccount;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "STATUS")
    private String status;
}
