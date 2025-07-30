package com.bender.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transfer(Account from, Account to, BigDecimal amount) {
        from.setBalance(from.getBalance().subtract(amount));
        accountRepository.save(from);

//        if (true) {
//            throw new IllegalStateException("cannot transfer from account " + from.getId() + " to account " + to.getId());
//        }

        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(to);
    }
}
