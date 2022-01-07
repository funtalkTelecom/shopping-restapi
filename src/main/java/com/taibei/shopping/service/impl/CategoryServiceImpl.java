package com.taibei.shopping.service.impl;

import com.taibei.shopping.repository.CategoryRepository;
import com.taibei.shopping.service.CategoriesService;
import com.taibei.shopping.entity.Category;
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
public class CategoryServiceImpl implements CategoriesService  {

	@Resource
	private CategoryRepository rep;


	 public Category save(Category obj) {
		 return rep.save(obj);
	 }


	 @Transactional
	 public List<Category> saveAll(Iterable<Category> list) {
		 return rep.saveAll(list);
	 }


	 public Category getOne(Integer id) {
		 return rep.getOne(id);
	 }


	 public Category findById(Integer id) {
		 Optional<Category> obj = rep.findById(id);
		 return obj.isPresent()?obj.get():null;
	 }


	 public void deleteById(Integer id) {
		 rep.deleteById(id);
	 }


	 @Transactional
	 public void deleteAll(List list) {
		 rep.deleteAll(list);
	 }


	 public void delete(Category obj) {
		 rep.delete(obj);
	 }


	 public boolean existsById(Integer id) {
		 return rep.existsById(id);
	 }


	 public long count() {
		 return rep.count();
	 }


	 public List<Category> findAll() {
		 return rep.findAll();
	 }


	 public List<Category> findAll(Category obj) {
		 List<Category> list = rep.findAll(Example.of(obj));
		 return list==null||list.size()<1?null:list;
	 }


	 public List<Category> findAll(Sort sort) {
		 return rep.findAll(sort);
	 }


	 public List<Category> findAllById(Iterable<Integer> ids) {
		 return rep.findAllById(ids);
	 }


	 public List<Category> findAll(Example<Category> e) {
		 return rep.findAll(e);
	 }


	 public List<Category> findAll(Example<Category> e, Sort sort) {
		 return rep.findAll(e,sort);
	 }


	 public Page<Category> findAll(Pageable page) {
		 return rep.findAll(page);
	 }


	 public Page<Category> findAll(Example<Category> e, Pageable page) {
		 return rep.findAll(e,page);
	 }


	 public Page<Category> findAll(Category obj, Pageable page) {
		 return rep.findAll(Example.of(obj),page);
	 }

	public Page<Category> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
				Sort.by(sortField).descending();

		Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
		return rep.findAll(pageable);
	}
}
