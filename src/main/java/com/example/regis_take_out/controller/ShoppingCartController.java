package com.example.regis_take_out.controller;

import com.alibaba.fastjson.serializer.BeanContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.regis_take_out.common.BaseContext;
import com.example.regis_take_out.common.R;
import com.example.regis_take_out.dto.DishDto;
import com.example.regis_take_out.pojo.ShoppingCart;
import com.example.regis_take_out.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User：H11
 * Date：2022/8/22
 * Description：
 */
@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        //设置用户id
        shoppingCart.setUserId(BaseContext.getCurrentId());
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        //判断当前是菜品还是套餐
        if(shoppingCart.getDishId() != null)
            queryWrapper.eq(ShoppingCart::getDishId, shoppingCart.getDishId());
        else queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        //查询当前菜品或套餐是否在购物车里面
        ShoppingCart one = shoppingCartService.getOne(queryWrapper);
        if(one == null){
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            return R.success(shoppingCart);
        }
        else{
            one.setNumber(one.getNumber()+1);
            shoppingCartService.updateById(one);
            return R.success(one);
        }
    }

    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        if(shoppingCart.getDishId() != null) queryWrapper.eq(ShoppingCart::getDishId, shoppingCart.getDishId());
        else queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        ShoppingCart one = shoppingCartService.getOne(queryWrapper);
        if(one.getNumber()==1) shoppingCartService.removeById(one);
        else {
            one.setNumber(one.getNumber()-1);
            shoppingCartService.updateById(one);
        }
        return R.success(one);
    }

    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(queryWrapper);
        return R.success(shoppingCartList);
    }
}
