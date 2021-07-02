package com.sg.bankaccount.domain.operation;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DepositOperationTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_if_amount_less_then_zero() {
        new DepositOperation(LocalDateTime.parse("2021-06-30T10:15:30.00"), -50.0);
    }

    @Test
    public void when_deposit_operation_executed_should_increase_balance_by_amount() {
        Operation operation = new DepositOperation(LocalDateTime.parse("2021-06-30T10:15:30.00"), 50);

        double balance = operation.execute(100);

        assertThat(balance, is(150.0));
    }
}
