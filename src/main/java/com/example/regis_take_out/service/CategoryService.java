package com.example.regis_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.regis_take_out.pojo.Category;

/**
 * User：H11
 * Date：2022/8/18
 * Description：
 */
public interface CategoryService extends IService<Category>{
    void delete(Long id);
}
