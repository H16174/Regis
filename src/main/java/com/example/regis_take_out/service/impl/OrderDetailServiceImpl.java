package com.example.regis_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.regis_take_out.mapper.OrderDetailMapper;
import com.example.regis_take_out.pojo.OrderDetail;
import com.example.regis_take_out.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * User：H11
 * Date：2022/8/22
 * Description：
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
