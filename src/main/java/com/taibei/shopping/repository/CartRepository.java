package com.taibei.shopping.repository;

import com.taibei.shopping.entity.Cart;
import com.taibei.shopping.entity.Expense;
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
public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart> {


    public Cart findByUserNameAndAndProductId(String userName, Integer productId);
    public Slice<Cart> findByUserName(String userName, Pageable pageable);


}
