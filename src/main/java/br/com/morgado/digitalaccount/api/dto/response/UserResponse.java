package br.com.morgado.digitalaccount.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String fullName;

    private String userName;

    private String email;

    private String password;

    private boolean verifiedEmail;
}
