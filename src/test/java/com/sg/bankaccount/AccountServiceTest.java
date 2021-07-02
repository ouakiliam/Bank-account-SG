package com.sg.bankaccount;

import com.sg.bankaccount.domain.Account;
import com.sg.bankaccount.presenter.Presenter;
import com.sg.bankaccount.presenter.StatementPresenter;
import com.sg.bankaccount.repository.InMemoryAccountRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountServiceTest {

	private AccountService accountService;
	private Account account;

	@Before
	public void setUp() {
		accountService = new FakeAccountService(new InMemoryAccountRepository(), new StatementPresenter());
		account = accountService.createAccount(new Account("1001"));
	}

	@Test
	public void can_make_a_deposit() {
		accountService.deposit(account.getId(), 100);

		assertThat(account.totalBalance(), is(100.0));
	}

	@Test
	public void can_make_a_deposit_then_a_withdrawal() {
		accountService.deposit(account.getId(), 500);
		accountService.withdrawal(account.getId(), 320);

		assertThat(account.totalBalance(), is(180.0));
	}

	@Test
	public void can_print_a_statement() {
		accountService.deposit(account.getId(), 500);
		accountService.withdrawal(account.getId(), 320);
		accountService.withdrawal(account.getId(), 100);
		accountService.withdrawal(account.getId(), 20);
		accountService.deposit(account.getId(), 30);

		String statement = accountService.getStatement(account.getId());

		assertThat(statement,
				is("operation - date - amount - totalBalance\n" + "Deposit - 15/03/2021 10:15:30 - 500.0 - 500.0\n"
						+ "Withdrawal - 30/04/2021 23:12:30 - 320.0 - 180.0\n"
						+ "Withdrawal - 01/05/2021 10:16:54 - 100.0 - 80.0\n"
						+ "Withdrawal - 05/05/2021 14:12:30 - 20.0 - 60.0\n"
						+ "Deposit - 06/05/2021 23:12:30 - 30.0 - 90.0"));
	}

	private class FakeAccountService extends AccountService {

		private int operationCount = 0;

		public FakeAccountService(InMemoryAccountRepository inMemoryAccountRepository, Presenter presenter) {
			super(inMemoryAccountRepository, presenter);
		}

		@Override
		protected LocalDateTime now() {
			operationCount++;
			switch (operationCount) {
			case 1:
				return LocalDateTime.parse("2021-03-15T10:15:30.00");
			case 2:
				return LocalDateTime.parse("2021-04-30T23:12:30.00");
			case 3:
				return LocalDateTime.parse("2021-05-01T10:16:54.00");
			case 4:
				return LocalDateTime.parse("2021-05-05T14:12:30.00");
			case 5:
				return LocalDateTime.parse("2021-05-06T23:12:30.00");
			default:
				return LocalDateTime.now();
			}
		}
	}
}
