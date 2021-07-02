package com.sg.bankaccount.domain.operation;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WithdrawOperationTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_if_amount_less_then_zero() {
        new WithdrawOperation(LocalDateTime.parse("2021-06-30T10:15:30.00"), 0);
    }

    @Test
    public void when_withdrawal_operation_executed_should_decrease_balance_by_amount() {
        Operation operation = new WithdrawOperation(LocalDateTime.parse("2021-06-30T10:15:30.00"), 50);

        double balance = operation.execute(100);

        assertThat(balance, is(50.0));
    }
    
    
    
}
