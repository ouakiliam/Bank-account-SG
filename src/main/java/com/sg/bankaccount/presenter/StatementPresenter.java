package com.sg.bankaccount.presenter;

import com.sg.bankaccount.domain.operation.Operation;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatementPresenter implements Presenter {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public String format(List<Operation> operations) {
        return operations.stream()
                .map(o -> o.name() + " - " + dateTimeFormatter.format(o.date()) + " - " + o.amount() + " - " + o.balance())
                .reduce("operation - date - amount - totalBalance", (a, b) -> a + "\n" + b);
    }
}
