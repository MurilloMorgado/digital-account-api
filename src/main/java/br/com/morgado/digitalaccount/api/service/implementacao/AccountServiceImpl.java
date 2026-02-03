package br.com.morgado.digitalaccount.api.service.implementacao;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.exception.DatabaseException;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;
import br.com.morgado.digitalaccount.api.service.AccountService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<AccountModel> findAllAccounts() {

        try {

            List<AccountModel> accounts = accountRepository.findAll();

            if (accounts.isEmpty()) {
                throw new ResourceNotFoundException("No accounts found");
            }

            return accounts;

        } catch (Exception e) {
            throw new DatabaseException("Error accessing the database.", e);
        }
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
