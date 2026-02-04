package br.com.morgado.digitalaccount.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String fullName;

    private String email;

    private Long agency;

    private Long currentAccount;

    private String bank;
}
