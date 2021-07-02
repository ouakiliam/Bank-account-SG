package com.sg.bankaccount.domain.operation;

import java.time.LocalDateTime;

public class WithdrawOperation extends Operation {


    public WithdrawOperation(LocalDateTime date, double amount) {
        super(date, amount);
    }

    public double execute(double previewsBalance) {
        this.balance = previewsBalance - amount;
        return this.balance;
    }

    @Override
    public String name() {
        return "Withdrawal";
    }
}
