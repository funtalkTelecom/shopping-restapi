package com.taibei.shopping.repository;

import com.taibei.shopping.entity.Expense;
import com.taibei.shopping.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
@author Xinpu Wang
*/
public interface ExpenseRepository extends JpaRepository<Expense, Integer>, JpaSpecificationExecutor<Expense> {

    public Slice<Expense> findByExpenseDateStartingWithOrderByExpenseDateDescCreateTimeDesc(String expenseDate, Pageable pageable);


    @Query(value = "SELECT coalesce(sum(amount),0) as total_amount  FROM tb_expense  WHERE  expense_date LIKE :expenseDate%", nativeQuery = true)
    public Double getSumAmountByDate(@Param("expenseDate") String expenseDate);

}
