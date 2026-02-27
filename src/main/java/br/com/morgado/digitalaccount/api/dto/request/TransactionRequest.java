package br.com.morgado.digitalaccount.api.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private AccountRequest sourceAccount;
    private AccountRequest destinationAccount;
    private BigDecimal amount;
}
