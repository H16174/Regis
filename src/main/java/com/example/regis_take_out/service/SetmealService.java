package com.example.regis_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.regis_take_out.dto.SetmealDto;
import com.example.regis_take_out.pojo.Setmeal;

/**
 * User：H11
 * Date：2022/8/18
 * Description：
 */
public interface SetmealService extends IService<Setmeal> {
    //新增套餐，同时保存与套餐相关的菜品信息
    void saveWithDish(SetmealDto setmealDto);
    //保存套餐，同时保存与套餐相关的菜品信息
    void updateWithDish(SetmealDto setmealDto);
}
