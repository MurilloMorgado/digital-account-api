package br.com.morgado.digitalaccount.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull
    @Min(100)
    @Max(999)
    private Long agency;

    @NotNull
    private Long currentAccount;

    // TODO Create a Customer
    @NotBlank
    private String customer;

    @NotBlank
    @Size(min = 6, max = 20)
    private String bank;

    @NotBlank
    private String accountType;

}
