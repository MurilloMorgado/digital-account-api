package br.com.morgado.digitalaccount.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private Long agency;

    private Long currentAccount;

    private String customer;

    private String bank;

    private String accountType;

}
