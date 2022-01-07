package com.taibei.shopping.controller;

import com.taibei.shopping.entity.Expense;
import com.taibei.shopping.entity.User;
import com.taibei.shopping.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/expense/getall/{pageNo}/{pageSize}")
    public ResponseEntity getAllProducts(@PathVariable Integer pageNo, @PathVariable  Integer pageSize){

        return ResponseEntity.ok(expenseService.findAll(PageRequest.of(pageNo-1,pageSize)));

    }

    @GetMapping("/getExpenseByDate/{date}/{pageNo}/{pageSize}")
    public ResponseEntity getExpenseByDate(@PathVariable String date,
                                          @PathVariable Integer pageNo,
                                          @PathVariable  Integer pageSize){

        return ResponseEntity.ok(expenseService.findByExpenseDate(date,PageRequest.of(pageNo-1,pageSize)));

    }

    @GetMapping("/getSumAmountByDate/{date}")
    public ResponseEntity getTotalFeeByDate(@PathVariable String date){

        return ResponseEntity.ok(expenseService.getSumAmountByDate(date));

    }

    @PostMapping("/createExpense")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){

        log.info(expense.toString());
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/addExpense").toUriString());

        return ResponseEntity.created(uri).body(expenseService.save(expense));

    }
}
