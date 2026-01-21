package br.com.morgado.digitalaccount.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    
    private String token;
    private String type = "Bearer";


    public LoginResponse(String token) {
        this.token = token;
    }

}
