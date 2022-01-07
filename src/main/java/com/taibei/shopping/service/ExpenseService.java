package com.taibei.shopping.service;


import com.taibei.shopping.entity.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ExpenseService extends BaseService<Expense, Integer> {


    public Slice<Expense> findByExpenseDate(String expenseDate, Pageable pageable);
    public Double getSumAmountByDate(String expenseDate);

}
