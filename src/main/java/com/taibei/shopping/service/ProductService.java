package com.taibei.shopping.service;

import com.taibei.shopping.entity.Cart;
import com.taibei.shopping.entity.HotKey;
import com.taibei.shopping.entity.Product;
import com.taibei.shopping.utils.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


/**
@author Xinpu Wang
*/
public interface ProductService extends BaseService<Product, Integer>  {


    Slice<Cart> findByUserName(String consumerName, Pageable pageable);
    Slice<Product> findByAreaType(Integer areaType, Pageable pageable);
    Slice<Product> findByProductNameLike(String productName, Pageable pageable);

    Slice<HotKey> findHotKey(Pageable pageable);


    public Result addCart(String consumerName, Integer productId);
    public Result deleteCart(String consumerName, Integer productId);
}
