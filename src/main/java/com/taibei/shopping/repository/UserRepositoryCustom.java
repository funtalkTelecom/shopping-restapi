package com.taibei.shopping.repository;


import com.taibei.shopping.entity.Order;
import com.taibei.shopping.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 @author Xinpu Wang
 */

public interface UserRepositoryCustom {

    public  List<User> findUser(int userId);
    public List<Order> findUserOrder(User user, Pageable pageable);
}
