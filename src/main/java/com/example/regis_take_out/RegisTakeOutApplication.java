package com.example.regis_take_out;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User：H11
 * Date：2022/8/16
 * Description：
 */
@SpringBootApplication
//扫描filter、interceptor等注解
@ServletComponentScan
//开启事务注解支持
@EnableTransactionManagement
public class RegisTakeOutApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisTakeOutApplication.class, args);
    }
}
