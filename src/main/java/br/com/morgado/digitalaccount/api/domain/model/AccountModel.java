package br.com.morgado.digitalaccount.api.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import br.com.morgado.digitalaccount.api.dto.response.AccountResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACCOUNT")
    private Long id;

    @Column(name = "AGENCY")
    private Long agency;

    @Column(name = "CURRENT_ACCOUNT")
    private Long currentAccount;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "BANK")
    private String bank;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "OVERDRAFT_LIMIT")
    private BigDecimal overdraftLimit;

    @Column(name = "OPENING_DATE")
    private Date openingDate;

    @Column(name = "CLOSING_DATE")
    private Date closingDate;

    @Column(name = "LAST_TRANSACTION")
    private Date lastTransaction;

    public AccountResponse toResponse() {
        return new AccountResponse(
                agency,
                currentAccount,
                customer,
                balance,
                bank,
                accountType,
                status,
                overdraftLimit,
                openingDate,
                closingDate,
                lastTransaction);
    }
}
