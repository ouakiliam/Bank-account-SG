package com.sg.bankaccount.repository;

import java.util.ArrayList;
import java.util.List;

import com.sg.bankaccount.domain.Account;

public class InMemoryAccountRepository implements AccountRepository {

	private List<Account> accounts = new ArrayList<>();

	@Override
	public Account save(Account account) {
		if (account != null) {
			account.setId(account.getId());
			accounts.add(account);
		}
		return account;
	}

	@Override
	public Account findById(String id) {
		return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
	}
}
