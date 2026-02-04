package br.com.morgado.digitalaccount.api.service.implementacao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.exception.AlreadyExistsException;
import br.com.morgado.digitalaccount.api.exception.DatabaseException;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.AccountRepository;
import br.com.morgado.digitalaccount.api.service.AccountService;
import jakarta.persistence.EntityExistsException;
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

        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }
    }

    @Override
    public AccountModel findAccountById(Long idAccount) {
        try {

            AccountModel account = accountRepository.findById(idAccount)
                    .orElseThrow(() -> new ResourceNotFoundException("No account found by ID: " + idAccount));

            return account;

        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }

    }

    @Override
    public Long createAccount(AccountModel account) {

        String customer = account.getCustomer();
        Optional<AccountModel> ac = accountRepository.findByCustomer(customer);

        if(ac.isPresent()){
            throw new AlreadyExistsException("Account already exists for the given customer: " + customer);
        }

        return accountRepository.save(account).getId();
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
