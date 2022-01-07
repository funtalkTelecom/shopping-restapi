package com.taibei.shopping.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Result {
    public static final int OK			= 200;//正常
    public static final int NOPOWER		= 300;//权限不足
    public static final int TIME_OUT	= 310;//会话超时
    public static final int WARN		= 400;//未知异常
    public static final int ERROR		= 500;//数据库等可预知的异常
    public static final int PARAM		= 999;//参数异常
    public static final int OTHER		= 888;//其他（可返前台）

    private Logger log = LoggerFactory.getLogger(Result.class);

    private int code;
    private Object data;

    public Result(int code, Object data){

        super();

        try {
            log.info("-----------返回结果:"+ code+",data:"+new ObjectMapper().writeValueAsString(data));
            this.code = code;
            this.data = data;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
