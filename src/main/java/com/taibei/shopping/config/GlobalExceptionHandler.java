package com.taibei.shopping.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final long serialVersionUID = 1L;

    //处理404错误,需要和配置文件中的配置参数配合完成
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity noHandlerFoundException(Exception e) {

        Map<String,String> errors = new HashMap<>();
        errors.put("errorMessage","No Handler available");

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    //处理各种查询结果为0的异常,前端拦截器中判断HttpStatus.NOT_FOUND,直接展示返回的字符串
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity notFoundHandler(NotFoundException e) {
//
//        Map<String,String> errors = new HashMap<>();
//        errors.put("errorMessage",e.getMessage());
//
//        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
//    }

    //其余异常的处理
    @ExceptionHandler(Exception.class)
    public ResponseEntity ExceptionHandler(Exception e) {

        Map<String,String> errors = new HashMap<>();
        errors.put("errorMessage",e.getMessage());
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);

    }

}