package br.com.morgado.digitalaccount.api.service.implementacao;

import java.util.List;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;

public interface AccountServiceImpl {

    List<AccountModel> findAllAccounts();

    AccountModel findAccountById(Long idAccount);

    Long createAccount(AccountModel account);
    
    void updateAccountDetails(Long idAccount, AccountModel account);
    
    void deleteAccount(Long idAccount);
    
}
