package com.example.regis_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.regis_take_out.dto.DishDto;
import com.example.regis_take_out.pojo.Dish;

/**
 * User：H11
 * Date：2022/8/18
 * Description：
 */
public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表
    void saveWithFlavor(DishDto dishDto);
    //保存菜品，同时插入菜品对应的口味数据，需要操作两张表
    void updateWithFlavor(DishDto dishDto);
}
