package com.example.regis_take_out.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * User：H11
 * Date：2022/8/16
 * Description：全局异常处理，底层是基于代理模式，会通过AOP拦截方法
 */

//拦截标注了@RestController的方法
@ControllerAdvice(annotations = RestController.class)
//返回json
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> sqlExceptionHandler(SQLIntegrityConstraintViolationException e){
        log.info("message：{}", e.getMessage());
        String msg = "保存出错！";
        if(e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split(" ");
            msg = "账号"+split[2]+"已存在！";
        }
        return R.error(msg);
    }
    @ExceptionHandler(MyCustomException.class)
    public R<String> myExceptionHandler(MyCustomException e){
        log.info("message：{}", e.getMessage());
        return R.error(e.getMessage());
    }
}
