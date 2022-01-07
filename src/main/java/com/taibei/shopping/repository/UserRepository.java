package com.taibei.shopping.repository;

import com.taibei.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
@author Xinpu Wang
*/
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {


    public User findByUserName(String userName);

}
