package com.sg.bankaccount.repository;


import com.sg.bankaccount.domain.Account;

public interface AccountRepository {
    Account save(Account account);

    Account findById(String id);
}
