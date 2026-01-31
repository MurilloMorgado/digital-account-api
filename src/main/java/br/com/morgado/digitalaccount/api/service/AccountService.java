package br.com.morgado.digitalaccount.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;
import br.com.morgado.digitalaccount.api.service.implementacao.AccountServiceImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountServiceImpl {

    private final AccountRepository accountRepository;

    @Override
    public List<AccountModel> findAllAccounts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllAccounts'");
    }

    @Override
    public AccountModel findAccountById(Long idAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAccountById'");
    }

    @Override
    public Long createAccount(AccountModel account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

    @Override
    public void updateAccountDetails(Long idAccount, AccountModel account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccountDetails'");
    }

    @Override
    public void deleteAccount(Long idAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }
    
}
