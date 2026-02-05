package br.com.morgado.digitalaccount.api.service.implementacao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.AccountModel;
import br.com.morgado.digitalaccount.api.dto.request.AccountRequest;
import br.com.morgado.digitalaccount.api.dto.response.AccountResponse;
import br.com.morgado.digitalaccount.api.exception.AlreadyExistsException;
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
    public List<AccountResponse> findAllAccounts() {

        try {

            List<AccountResponse> accounts = accountRepository.findAll().stream().map(AccountModel::toResponse)
                    .collect(Collectors.toList());

            if (accounts.isEmpty()) {
                throw new ResourceNotFoundException("No accounts found");
            }

            return accounts;

        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }
    }

    @Override
    public AccountResponse findAccountById(Long idAccount) {
        try {

            AccountModel account = accountRepository.findById(idAccount)
                    .orElseThrow(() -> new ResourceNotFoundException("No account found by ID: " + idAccount));

            return account.toResponse();

        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }

    }

    @Override
    public Long createAccount(AccountRequest account) {

        String customer = account.getCustomer();
        Optional<AccountModel> ac = accountRepository.findByCustomer(customer);

        if (ac.isPresent()) {
            throw new AlreadyExistsException("Account already exists for the given customer: " + customer);
        }

        AccountModel accountModel = new AccountModel(account);

        return accountRepository.save(accountModel).getId();
    }

    @Override
    public void updateAccountDetails(Long idAccount, AccountRequest account) {
        AccountModel accountModel = accountRepository.findById(idAccount)
                .orElseThrow(() -> new ResourceNotFoundException("No account found by ID: " + idAccount));

        BeanUtils.copyProperties(account, accountModel, "id");

        accountRepository.save(accountModel);
    }

    @Override
    public void deleteAccount(Long idAccount) {

        findAccountById(idAccount);

        accountRepository.deleteById(idAccount);

    }

}
