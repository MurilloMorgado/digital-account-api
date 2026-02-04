package br.com.morgado.digitalaccount.api.service;

import java.util.List;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.dto.response.AccountResponse;

public interface AccountService {

    List<AccountResponse> findAllAccounts();

    AccountResponse findAccountById(Long idAccount);

    Long createAccount(AccountModel account);

    void updateAccountDetails(Long idAccount, AccountModel account);

    void deleteAccount(Long idAccount);

}
