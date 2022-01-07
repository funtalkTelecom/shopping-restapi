package com.taibei.shopping.controller;

import com.taibei.shopping.entity.Role;
import com.taibei.shopping.entity.User;
import com.taibei.shopping.service.UserService;
import com.taibei.shopping.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;



@RestController  @Slf4j
@RequestMapping("/api/v1")

public class UserController {


    @Autowired  private UserService userService;

    @GetMapping("/getallusers/{pageNo}/{pageSize}")
    public Result getAllUsers(@PathVariable Integer pageNo,
                              @PathVariable  Integer pageSize){

       return new Result(Result.OK, userService.findAll(PageRequest.of(pageNo,pageSize)));

    }

    @GetMapping("/getuserbyname/{userName}")
    public ResponseEntity getAllUsers(@PathVariable  String userName){

        return  ResponseEntity.ok(userService.findByUserName(userName));

    }

    @GetMapping("/userlogout")
    public ResponseEntity userLogout(){

        log.info("------userlogout--------------------");
        return  ResponseEntity.ok().build();

    }



    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(User user){

        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/adduser").toUriString());

        return ResponseEntity.created(uri).body(userService.save(user));

    }


    @PostMapping("/registerConsumer")
    public ResponseEntity<User> registerConsumer(@RequestBody User user){

        log.info("------registerConsumer--------------------");
        log.info(user.toString());

        user.setType("2");

        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/registerConsumer").toUriString());

        return ResponseEntity.created(uri).body(userService.save(user));

    }

    @GetMapping("/getrole/{roleid}")
    public ResponseEntity getRole(@PathVariable  Integer roleid){

        return  ResponseEntity.ok(userService.findRoleById(roleid));

    }


    @PostMapping("/addrole")
    public ResponseEntity<Role> addRole(Role role){

        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/addrole").toUriString());

        return ResponseEntity.created(uri).body(userService.addRole(role));

    }


    @PostMapping("/addroletouser")
    public ResponseEntity<?> addRoleToUser(String userName,String roleName){

        userService.addRoleToUser(userName,roleName);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        userService.refreshToken(request,response);

    }


    @GetMapping("/getSearchLog/{pageNo}/{pageSize}")
    public ResponseEntity getSearchLog(
                              @PathVariable Integer pageNo,
                              @PathVariable  Integer pageSize){

        return ResponseEntity.ok(userService.findSearchLog(PageRequest.of(pageNo-1,pageSize)));

    }

    @GetMapping("/deleteSearchKey/{searchKey}")
    public ResponseEntity deleteSearchKey(@PathVariable String searchKey){

        return ResponseEntity.ok(userService.deleteSearchKey(searchKey));

    }




/*    @Bean    //在项目启动后自动执行的功能
    CommandLineRunner commandLineRunner(UserService userService){

       return  args -> { new Result(Result.OK,userService.findAll());};

    }*/


}
