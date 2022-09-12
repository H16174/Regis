package com.example.regis_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.regis_take_out.pojo.Orders;

/**
 * User：H11
 * Date：2022/8/22
 * Description：
 */
public interface OrderService extends IService<Orders> {
    /**
     * 用户下单
     * @param orders
     */
    void submit(Orders orders);
}
