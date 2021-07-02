package com.sg.bankaccount.presenter;

import com.sg.bankaccount.domain.operation.Operation;

import java.util.List;

public interface Presenter {

    String format(List<Operation> operations);
}
