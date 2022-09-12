package com.example.regis_take_out.common;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User：H11
 * Date：2022/8/16
 * Description：通用的返回结果类，服务端响应的数据都会封装成这个数据类型
 */

@Data
public class R<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> res = new R<T>();
        res.data = object;
        res.code = 1;
        return res;
    }


    public static <T> R<T> error(String msg) {
        R<T> res = new R<T>();
        res.msg = msg;
        res.code = 0;
        return res;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }



}

