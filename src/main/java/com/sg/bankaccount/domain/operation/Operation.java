package com.sg.bankaccount.domain.operation;

import java.time.LocalDateTime;

public abstract class Operation {

    private final LocalDateTime date;
    protected final double amount;
    protected double balance;

    public Operation(LocalDateTime date, double amount) {
        if(amount <= 0)
            throw new IllegalArgumentException("Amount should not be less then 0");
        this.date = date;
        this.amount = amount;
    }


    public abstract double execute(double previewsBalance);
    public abstract String name();

    public LocalDateTime date(){
        return date;
    }

    public double amount(){
        return amount;
    }

    public double balance(){
        return balance;
    }
}
