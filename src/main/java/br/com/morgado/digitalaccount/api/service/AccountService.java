package br.com.morgado.digitalaccount.api.service;

import java.util.List;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;

public interface AccountService {

    List<AccountModel> findAllAccounts();

    AccountModel findAccountById(Long idAccount);

    Long createAccount(AccountModel account);

    void updateAccountDetails(Long idAccount, AccountModel account);

    void deleteAccount(Long idAccount);

}
