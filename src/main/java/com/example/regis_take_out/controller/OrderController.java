package com.example.regis_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.regis_take_out.common.BaseContext;
import com.example.regis_take_out.common.R;
import com.example.regis_take_out.pojo.Orders;
import com.example.regis_take_out.service.OrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User：H11
 * Date：2022/8/22
 * Description：
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);
        return R.success("ss");
    }

    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize, Long number){
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(number!=null, Orders::getNumber, number);
        queryWrapper.in(Orders::getStatus, 1, 2, 3);
        orderService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> editStatus(@RequestBody Orders orders){
        Orders order = orderService.getById(orders.getId());
        if(order == null) return R.error("订单不存在！");
        order.setStatus(order.getStatus());
        orderService.updateById(order);
        return R.success("派送成功！");
    }

    @GetMapping("/userPage")
    public R<Page> userPage(Integer page, Integer pageSize){
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, BaseContext.getCurrentId());
        orderService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }
}
