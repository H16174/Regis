package com.example.regis_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.regis_take_out.common.BaseContext;
import com.example.regis_take_out.common.R;
import com.example.regis_take_out.pojo.User;
import com.example.regis_take_out.service.UserService;
import com.example.regis_take_out.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * User：H11
 * Date：2022/8/22
 * Description：
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    //读取yml中的username赋给from
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;


    /**
     * 发送验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpServletRequest request){
        //获取邮箱
        String phone = user.getPhone();
        if(StringUtils.hasLength(phone)) {
            //随机生成验证码
            String code = String.valueOf(ValidateCodeUtils.generateValidateCode(4));
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(phone);
            message.setSubject("您本次的验证码是");
            message.setText("尊敬的xxx,您好:\n"
                    + "\n本次请求的邮件验证码为:" + code + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                    + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");
            javaMailSender.send(message);
            //将验证码保存到session域中
            request.getSession().setAttribute("code", code);
            return R.success("验证发送成功！");
        }
        else return R.error("短信发送失败！");
    }
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpServletRequest request){
        String phone = (String) map.get("phone");
        String code = (String) map.get("code");
        //若验证码正确，登录成功
        if(code!=null && code.equals(request.getSession().getAttribute("code"))){
            //判断当前用户是否为新用户，若为新用户帮其注册
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            request.getSession().setAttribute("user", user.getId());
            return R.success(user);
        }
        else return R.error("验证码错误！");
    }

    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return R.success("退出登录成功！");
    }
}
