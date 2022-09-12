package com.example.regis_take_out.dto;


import com.example.regis_take_out.pojo.Setmeal;
import com.example.regis_take_out.pojo.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
