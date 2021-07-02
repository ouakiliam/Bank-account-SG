package com.sg.bankaccount.repository;

import com.sg.bankaccount.domain.Account;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class InMemoryAccountRepositoryTest {

    private AccountRepository accountRepository;
    private Account account ; 

    @Before
    public void setUp() {
        accountRepository = new InMemoryAccountRepository();
        account = new Account("1001");
    }

    @Test
    public void can_save_an_account() {
        Account getAccount = accountRepository.save(account);

        assertNotNull(getAccount.getId());
    }


    @Test
    public void can_find_an_account() {
        Account getAccount = accountRepository.save(account);

        Account foundedAccount = accountRepository.findById(getAccount.getId());

        assertThat(account, CoreMatchers.is(foundedAccount));
    }
}
