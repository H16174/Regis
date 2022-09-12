package com.example.regis_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.regis_take_out.mapper.AddressBookMapper;
import com.example.regis_take_out.pojo.AddressBook;
import com.example.regis_take_out.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * User：H11
 * Date：2022/8/22
 * Description：
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}