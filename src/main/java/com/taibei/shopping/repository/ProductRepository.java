package com.taibei.shopping.repository;

import com.taibei.shopping.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
@author Xinpu Wang
*/
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


    public Slice<Product> findByAreaType(Integer areaType, Pageable pageable);
    public Slice<Product> findByProductNameLike(String productName, Pageable pageable);



}
