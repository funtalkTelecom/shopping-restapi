package com.taibei.shopping;

import com.taibei.shopping.entity.Order;
import com.taibei.shopping.entity.Role;
import com.taibei.shopping.entity.User;
import com.taibei.shopping.repository.OrderRepository;
import com.taibei.shopping.repository.UserRepository;
import com.taibei.shopping.repository.UserRepositoryCustom;
import com.taibei.shopping.service.UserService;
import com.taibei.shopping.utils.Result;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Array;
import java.util.List;



@SpringBootTest
class ShoppingApplicationTests {

    private Logger logger = LoggerFactory.getLogger(Result.class);
    @Autowired
    UserRepositoryCustom userRepositoryCustom;

    @Autowired UserRepository  userRepository;
    @Autowired OrderRepository orderRepository;
    @Autowired UserService userService;


    @Test
    void addUser() {



    /*  // 测试 onetomany

        User user =new User("王新谱","123456","18611602701","qiluwxp@126.com");

        Order order1= new Order(3);
        Order order2= new Order(4);

        user.getOrders().add(order1);
        user.getOrders().add(order2);

        //下面关系必须显示编码确定，如果不编码就要在one方定义@cJoinColumn标签
        order1.setUser(user);
        order2.setUser(user);

        orderRepository.save(order1);
        orderRepository.save(order2);

        userRepository.save(user);
        */


        // 测试 manytomany

        User user =new User("王新谱","123456","18611602701","qiluwxp@126.com");

        Role role1 =new Role();
        role1.setRoleName("ROLE_USER");


        // 相互之间的关系必须要在代码中设定 user加role, role加user
        user.getRoles().add(role1);
        role1.getUsers().add(user);

        userService.save(user); //必须通过子来保存双方；通过在子一方配置cascade,会关联保存父;
                                // 无论是onetomany还是manytomany，都是通过子来保存双方数据，用户和角色，用户是子，角色是父！






    }



    @Test
    void contextLoads() {

        User user =new User("王一忱","123456","18611602701","qiluwxp@126.com");

        Pageable pageable= PageRequest.of(2,5);
        logger.info("---return from database----->"+userRepositoryCustom.findUserOrder(user,pageable).toString());

    }

}
