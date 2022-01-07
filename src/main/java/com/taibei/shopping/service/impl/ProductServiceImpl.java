package com.taibei.shopping.service.impl;

import com.taibei.shopping.entity.Cart;
import com.taibei.shopping.entity.HotKey;
import com.taibei.shopping.entity.SearchLog;
import com.taibei.shopping.repository.CartRepository;
import com.taibei.shopping.repository.HotKeyRepository;
import com.taibei.shopping.repository.ProductRepository;
import com.taibei.shopping.repository.SearchLogRepository;
import com.taibei.shopping.service.ProductService;
import com.taibei.shopping.entity.Product;
import java.util.List;

import com.taibei.shopping.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import java.util.Optional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
@author Xinpu Wang
*/
@Service @Slf4j
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductRepository productRepo;
	@Resource
	private CartRepository cartRepo;
	@Resource
	private HotKeyRepository hotKeyRepository;
	@Resource
	private SearchLogRepository searchLogRepository;

	public Slice<Cart> findByUserName(String userName,Pageable pageable){

		return  cartRepo.findByUserName(userName,pageable);
	}

	public Slice<Product> findByAreaType(Integer areaType, Pageable pageable){

		return  productRepo.findByAreaType(areaType,pageable);
	}

	@Transactional
	public Slice<Product> findByProductNameLike(String productName, Pageable pageable){



		String  userName=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		log.info("-------findByProductNameLike---------"+userName);

		searchLogRepository.deleteByUserNameAndSearchKey(userName,productName);

		SearchLog searchLog =new SearchLog();
		searchLog.setUserName(userName);
		searchLog.setSearchKey(productName);
		searchLogRepository.save(searchLog);

		return productRepo.findByProductNameLike("%"+productName+"%",pageable);
	}

	public Slice<HotKey> findHotKey(Pageable pageable){

		return hotKeyRepository.findAll(pageable);
	}


	@Transactional
	public Result addCart(String userName, Integer productId){

		Product product= productRepo.getOne(productId);
		product.setQuantity(product.getQuantity()-1);
		productRepo.save(product);

		Cart  cart =cartRepo.findByUserNameAndAndProductId(userName,productId);
		if (cart != null)
			cart.setQuantity(cart.getQuantity()+1);
		else {
			cart = new Cart();
			cart.setUserName(userName);
			cart.setProductId(productId);
			cart.setCategory(product.getCategory());
			cart.setPrice(product.getPrice());
			cart.setQuantity(1);
		}

		cartRepo.save(cart);

		return  new Result(Result.OK,"adding successful.");
		
	}

	@Transactional
	public Result deleteCart(String userName, Integer productId){

		Cart  cart =cartRepo.findByUserNameAndAndProductId(userName,productId);

		if (cart.getQuantity() <= 1)
			cartRepo.delete(cart);
		else
			cart.setQuantity(cart.getQuantity()-1);

		cartRepo.save(cart);

		Product product= productRepo.getOne(productId);
		product.setQuantity(product.getQuantity()+1);

		productRepo.save(product);

		return  new Result(Result.OK,"deleting successful.");

	}



	 public Product save(Product obj) {
		 return productRepo.save(obj);
	 }


	 @Transactional
	 public List<Product> saveAll(Iterable<Product> list) {
		 return productRepo.saveAll(list);
	 }


	 public Product getOne(Integer id) {
		 return productRepo.getOne(id);
	 }


	 public Product findById(Integer id) {
		 Optional<Product> obj = productRepo.findById(id);
		 return obj.isPresent()?obj.get():null;
	 }


	 public void deleteById(Integer id) {
		 productRepo.deleteById(id);
	 }


	 @Transactional
	 public void deleteAll(List list) {
		 productRepo.deleteAll(list);
	 }


	 public void delete(Product obj) {
		 productRepo.delete(obj);
	 }


	 public boolean existsById(Integer id) {
		 return productRepo.existsById(id);
	 }


	 public long count() {
		 return productRepo.count();
	 }


	 public List<Product> findAll() {
		 return productRepo.findAll();
	 }


	 public List<Product> findAll(Product obj) {
		 List<Product> list = productRepo.findAll(Example.of(obj));
		 return list==null||list.size()<1?null:list;
	 }


	 public List<Product> findAll(Sort sort) {
		 return productRepo.findAll(sort);
	 }


	 public List<Product> findAllById(Iterable<Integer> ids) {
		 return productRepo.findAllById(ids);
	 }


	 public List<Product> findAll(Example<Product> e) {
		 return productRepo.findAll(e);
	 }


	 public List<Product> findAll(Example<Product> e, Sort sort) {
		 return productRepo.findAll(e,sort);
	 }


	 public Page<Product> findAll(Pageable page) {
		 return productRepo.findAll(page);
	 }


	 public Page<Product> findAll(Example<Product> e, Pageable page) {
		 return productRepo.findAll(e,page);
	 }


	 public Page<Product> findAll(Product obj, Pageable page) {
		 return productRepo.findAll(Example.of(obj),page);
	 }

	 public Page<Product> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
				Sort.by(sortField).descending();

		Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
		return productRepo.findAll(pageable);
	 }
}
