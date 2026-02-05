package br.com.morgado.digitalaccount.api.service;

import java.util.List;

import br.com.morgado.digitalaccount.api.dto.request.AccountRequest;
import br.com.morgado.digitalaccount.api.dto.response.AccountResponse;

public interface AccountService {

    List<AccountResponse> findAllAccounts();

    AccountResponse findAccountById(Long idAccount);

    Long createAccount(AccountRequest account);

    void updateAccountDetails(Long idAccount, AccountRequest account);

    void deleteAccount(Long idAccount);

}
