package com.sg.bankaccount.presenter;

import com.sg.bankaccount.domain.operation.DepositOperation;
import com.sg.bankaccount.domain.operation.Operation;
import com.sg.bankaccount.domain.operation.WithdrawOperation;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StatementPresenterTest {

    private Presenter presenter;

    @Before
    public void setUp() {
        presenter = new StatementPresenter();
    }

    @Test
    public void when_no_operation_should_display_empty_statement() {
        String statement = presenter.format(emptyList());
        assertThat(statement, is("operation - date - amount - totalBalance"));
    }

    @Test
    public void when_deposit_operation_executed_should_be_displayed_in_statement() {
        double totalBalance = 100;
        Operation depositOperation = new DepositOperation(LocalDateTime.parse("2021-06-30T10:15:30.00"), 50);
        depositOperation.execute(totalBalance);

        String statement = presenter.format(asList(depositOperation));

        assertThat(statement, is("operation - date - amount - totalBalance\n" +
                "Deposit - 30/06/2021 10:15:30 - 50.0 - 150.0"));
    }

    @Test
    public void when_deposit_and_withdrawal_operations_executed_should_be_displayed_in_statement() {
        double totalBalance = 100;
        Operation depositOperation = new DepositOperation(LocalDateTime.parse("2021-06-30T10:15:30.00"), 50);
        Operation withdrawalOperation = new WithdrawOperation(LocalDateTime.parse("2021-07-05T23:12:30.00"), 70);
        totalBalance = depositOperation.execute(totalBalance);
        withdrawalOperation.execute(totalBalance);

        String statement = presenter.format(asList(depositOperation, withdrawalOperation));

        assertThat(statement, is("operation - date - amount - totalBalance\n" +
                "Deposit - 30/06/2021 10:15:30 - 50.0 - 150.0\n" +
                "Withdrawal - 05/07/2021 23:12:30 - 70.0 - 80.0"));
    }
}
