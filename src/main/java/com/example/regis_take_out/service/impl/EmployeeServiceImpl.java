package com.example.regis_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.regis_take_out.mapper.EmployeeMapper;
import com.example.regis_take_out.pojo.Employee;
import com.example.regis_take_out.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * User：H11
 * Date：2022/8/16
 * Description：
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
}
