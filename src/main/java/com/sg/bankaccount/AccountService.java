package com.sg.bankaccount;

import com.sg.bankaccount.domain.Account;
import com.sg.bankaccount.domain.operation.DepositOperation;
import com.sg.bankaccount.domain.operation.WithdrawOperation;
import com.sg.bankaccount.presenter.Presenter;
import com.sg.bankaccount.repository.AccountRepository;

import java.time.LocalDateTime;

public class AccountService {

    private AccountRepository accountRepository;
    private Presenter presenter;

    public AccountService(AccountRepository accountRepository, Presenter presenter) {
        this.accountRepository = accountRepository;
        this.presenter = presenter;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deposit(String accountId, int amount) {
        Account account = accountRepository.findById(accountId);
        account.process(new DepositOperation(now(), amount));
    }

    public void withdrawal(String accountId, int amount) {
        Account account = accountRepository.findById(accountId);
        account.process(new WithdrawOperation(now(), amount));
    }

    public String getStatement(String accountId) {
        return presenter.format(accountRepository.findById(accountId).operations());
    }

    protected LocalDateTime now() {
        return LocalDateTime.now();
    }
}
