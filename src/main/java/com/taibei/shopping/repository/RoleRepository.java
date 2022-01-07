package com.taibei.shopping.repository;

import com.taibei.shopping.entity.Role;
import com.taibei.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
@author Xinpu Wang
*/
public interface RoleRepository extends JpaRepository<Role, Integer>,JpaSpecificationExecutor<Role> {


   public Role findByRoleName(String roleName);

}
