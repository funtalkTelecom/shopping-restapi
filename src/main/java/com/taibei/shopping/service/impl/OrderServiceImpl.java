package com.taibei.shopping.service.impl;

import com.taibei.shopping.repository.OrderRepository;
import com.taibei.shopping.service.OrderService;
import com.taibei.shopping.entity.Order;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import java.util.Optional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
@author Xinpu Wang
*/
@Service
public class OrderServiceImpl  implements OrderService  {

	@Resource
	private OrderRepository rep;


	 public Order save(Order obj) {
		 return rep.save(obj);
	 }


	 @Transactional
	 public List<Order> saveAll(Iterable<Order> list) {
		 return rep.saveAll(list);
	 }


	 public Order getOne(Integer id) {
		 return rep.getOne(id);
	 }


	 public Order findById(Integer id) {
		 Optional<Order> obj = rep.findById(id);
		 return obj.isPresent()?obj.get():null;
	 }


	 public void deleteById(Integer id) {
		 rep.deleteById(id);
	 }


	 @Transactional
	 public void deleteAll(List list) {
		 rep.deleteAll(list);
	 }


	 public void delete(Order obj) {
		 rep.delete(obj);
	 }


	 public boolean existsById(Integer id) {
		 return rep.existsById(id);
	 }


	 public long count() {
		 return rep.count();
	 }


	 public List<Order> findAll() {
		 return rep.findAll();
	 }


	 public List<Order> findAll(Order obj) {
		 List<Order> list = rep.findAll(Example.of(obj));
		 return list==null||list.size()<1?null:list;
	 }


	 public List<Order> findAll(Sort sort) {
		 return rep.findAll(sort);
	 }


	 public List<Order> findAllById(Iterable<Integer> ids) {
		 return rep.findAllById(ids);
	 }


	 public List<Order> findAll(Example<Order> e) {
		 return rep.findAll(e);
	 }


	 public List<Order> findAll(Example<Order> e, Sort sort) {
		 return rep.findAll(e,sort);
	 }


	 public Page<Order> findAll(Pageable page) {
		 return rep.findAll(page);
	 }


	 public Page<Order> findAll(Example<Order> e, Pageable page) {
		 return rep.findAll(e,page);
	 }


	 public Page<Order> findAll(Order obj, Pageable page) {
		 return rep.findAll(Example.of(obj),page);
	 }

	 public Page<Order> findAll(Integer pageNo,Integer pageSize,String sortField,String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
				Sort.by(sortField).descending();

		Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
		return rep.findAll(pageable);
	 }

}
