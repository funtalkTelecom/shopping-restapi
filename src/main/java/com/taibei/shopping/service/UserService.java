package com.taibei.shopping.service;

import com.taibei.shopping.entity.Role;
import com.taibei.shopping.entity.SearchLog;
import com.taibei.shopping.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
@author Xinpu Wang
*/
public interface UserService extends BaseService<User, Integer>  {

     User findByUserName(String userName);

     Role addRole(Role role);

     void addRoleToUser(String userName,String roleName);

     void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

     Slice<Object> findSearchLog(Pageable pageable);

     long deleteSearchKey(String searchKey);

     Role findRoleById(Integer roleId);

}
