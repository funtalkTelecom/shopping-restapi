package com.taibei.shopping.controller;


import com.taibei.shopping.entity.HotKey;
import com.taibei.shopping.service.ProductService;
import com.taibei.shopping.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ProdController {

    @Autowired ProductService productService;

    @GetMapping("/getAllProduct/{pageNo}/{pageSize}")
    public ResponseEntity getAllProducts(@PathVariable Integer pageNo,@PathVariable  Integer pageSize){

      return ResponseEntity.ok(productService.findAll(PageRequest.of(pageNo-1,pageSize)));

    }

    @GetMapping("/getProduct/{areaType}/{pageNo}/{pageSize}")
    public ResponseEntity getProduct(@PathVariable Integer areaType,
                                     @PathVariable Integer pageNo,
                                     @PathVariable  Integer pageSize){

        return ResponseEntity.ok(productService.findByAreaType(areaType,PageRequest.of(pageNo-1,pageSize)));

    }

    @GetMapping("/getProductById/{productId}")
    public ResponseEntity getProductById(@PathVariable Integer productId){

        return ResponseEntity.ok(productService.findById(productId));

    }

    @GetMapping("/getProductByProductName/{productName}/{pageNo}/{pageSize}")
    public ResponseEntity getProductByProductName(@PathVariable String productName,
                                                  @PathVariable Integer pageNo,
                                                  @PathVariable  Integer pageSize){

        return ResponseEntity.ok(productService.findByProductNameLike(productName,PageRequest.of(pageNo-1,pageSize)));

    }


    @GetMapping("/getHotKey/{pageNo}/{pageSize}")
    public ResponseEntity getHotKey(@PathVariable Integer pageNo,
                                    @PathVariable  Integer pageSize){

        Sort sort = Sort.by(Sort.Direction.ASC, "priority").and(Sort.by(Sort.Direction.ASC, "createTime"));

        return ResponseEntity.ok(productService.findHotKey(PageRequest.of(pageNo-1,pageSize,sort)));

    }


    @GetMapping("/getCartByConsumerId/{consumerId}/{pageNo}/{pageSize}")
    public ResponseEntity getCartByConsumerId(@PathVariable String consumerId,
                                         @PathVariable Integer pageNo,
                                         @PathVariable  Integer pageSize){

        return ResponseEntity.ok(productService.findByUserName(consumerId,PageRequest.of(pageNo-1,pageSize)));

    }

    @GetMapping("/addCart/{userName}/{productId}")
    public ResponseEntity addCart( @PathVariable String userName,  @PathVariable Integer productId){

        log.info("------------->"+userName+"----------"+productId);
        return ResponseEntity.ok(productService.addCart(userName,productId));

    }

    @GetMapping("/deleteCart/{userName}/{productId}")
    public ResponseEntity deleteCart( @PathVariable String userName,  @PathVariable Integer productId){

        return ResponseEntity.ok(productService.deleteCart(userName,productId));

    }


}
