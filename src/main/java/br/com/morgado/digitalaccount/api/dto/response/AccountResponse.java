package br.com.morgado.digitalaccount.api.dto.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long agency;

    private Long currentAccount;

    private String customer;

    private BigDecimal balance;

    private String bank;

    private String accountType;

    private String status;

    private BigDecimal overdraftLimit;

    private Date openingDate;

    private Date closingDate;

    private Date lastTransaction;
}
