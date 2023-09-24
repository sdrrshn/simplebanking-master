package com.eteration.simplebanking.services;
import com.eteration.simplebanking.exceptions.AccountNotFoundException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class AccountService {

    private  final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new AccountNotFoundException("Account Not Found " + accountNumber);
        }
    }


    public Account save(Account account) {
        return accountRepository.save(account);

    }

}
