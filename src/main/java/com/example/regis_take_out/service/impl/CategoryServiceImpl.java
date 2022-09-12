package com.example.regis_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.regis_take_out.common.MyCustomException;
import com.example.regis_take_out.mapper.CategoryMapper;
import com.example.regis_take_out.pojo.Category;
import com.example.regis_take_out.pojo.Dish;
import com.example.regis_take_out.pojo.Setmeal;
import com.example.regis_take_out.service.CategoryService;
import com.example.regis_take_out.service.DishService;
import com.example.regis_take_out.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User：H11
 * Date：2022/8/18
 * Description：
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{
    @Autowired
    DishService dishService;
    @Autowired
    SetmealService setmealService;
    /**
     * 根据id删除菜品分类，删除之前进行判断
     * @param id
     */
    @Override
    public void delete(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        //查询当前分类是否关联有菜品，若已经关联，抛业务异常
        if(dishService.count(dishLambdaQueryWrapper) > 0){
            throw new MyCustomException("当前分类下关联有菜品，删除失败！");
        }
        //查询当前分类是否关联有套餐，若已经关联，抛业务异常
        if(setmealService.count(setmealLambdaQueryWrapper) > 0){
            throw new MyCustomException("当前分类下关联有套餐，删除失败！");
        }
        super.removeById(id);
    }
}
