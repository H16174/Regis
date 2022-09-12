package com.example.regis_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.regis_take_out.mapper.UserMapper;
import com.example.regis_take_out.pojo.User;
import com.example.regis_take_out.service.UserService;
import org.springframework.stereotype.Service;

/**
 * User：H11
 * Date：2022/8/22
 * Description：
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
