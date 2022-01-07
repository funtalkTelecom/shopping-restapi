package com.taibei.shopping.service.impl;

import com.taibei.shopping.entity.Expense;
import com.taibei.shopping.repository.ExpenseRepository;
import com.taibei.shopping.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
@author Xinpu Wang
*/
@Service @RequiredArgsConstructor @Transactional  @Slf4j
public class ExpenseServiceImpl implements ExpenseService {

     @Autowired  private ExpenseRepository expenseRepository;


	public Slice<Expense> findByExpenseDate(String expenseDate,Pageable pageable){

	   return  expenseRepository.findByExpenseDateStartingWithOrderByExpenseDateDescCreateTimeDesc(expenseDate,pageable);

	}

	public Double getSumAmountByDate(String expenseDate){

		return expenseRepository.getSumAmountByDate(expenseDate);
	}


	public Expense save(Expense obj) {
	 	return expenseRepository.save(obj);
	 }

	 @Transactional
	 public List<Expense> saveAll(Iterable<Expense> list) {

		 return expenseRepository.saveAll(list);

	 }

	 public Expense getOne(Integer id) {
		 return expenseRepository.getOne(id);
	 }


	 public Expense findById(Integer id) {
		 Optional<Expense> obj = expenseRepository.findById(id);
		 return obj.isPresent()?obj.get():null;
	 }


	 public void deleteById(Integer id) {
		 expenseRepository.deleteById(id);
	 }


	 @Transactional
	 public void deleteAll(List list) {
		 expenseRepository.deleteAll(list);
	 }


	 public void delete(Expense obj) {
		 expenseRepository.delete(obj);
	 }


	 public boolean existsById(Integer id) {
		 return expenseRepository.existsById(id);
	 }


	 public long count() {
		 return expenseRepository.count();
	 }


	 public List<Expense> findAll() {
		 return expenseRepository.findAll();
	 }


	 public List<Expense> findAll(Expense obj) {
		 List<Expense> list = expenseRepository.findAll(Example.of(obj));
		 return list==null||list.size()<1?null:list;
	 }


	 public List<Expense> findAll(Sort sort) {
		 return expenseRepository.findAll(sort);
	 }


	 public List<Expense> findAllById(Iterable<Integer> ids) {
		 return expenseRepository.findAllById(ids);
	 }


	 public List<Expense> findAll(Example<Expense> e) {
		 return expenseRepository.findAll(e);
	 }


	 public Page<Expense> findAll(Pageable pageable) {
		 return expenseRepository.findAll(pageable);
	 }


	 public List<Expense> findAll(Example<Expense> e, Sort sort) {
		return expenseRepository.findAll(e,sort);
	}


	 public Page<Expense> findAll(Example<Expense> e, Pageable page) {
		 return expenseRepository.findAll(e,page);
	 }


	 public Page<Expense> findAll(Expense obj, Pageable page) {
		 return expenseRepository.findAll(Example.of(obj),page);
	 }

	@Override
	public Page<Expense> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection) {
		return null;
	}
}
