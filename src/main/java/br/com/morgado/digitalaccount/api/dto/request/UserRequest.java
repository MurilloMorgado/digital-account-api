package br.com.morgado.digitalaccount.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String fullName;

    private String userName;

    private String email;
    
    private String password;

}
