package com.sg.bankaccount.domain;

import com.sg.bankaccount.domain.operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public Account(String id) {
		super();
		this.id = id;
	}

	private String id;
    private double totalBalance = 0;
    private List<Operation> operations = new ArrayList<>();

    public double totalBalance() {
        return totalBalance;
    }

    public void process(Operation operation) {
        operations.add(operation);
        totalBalance = operation.execute(totalBalance);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Operation> operations() {
        return operations;
    }
}
