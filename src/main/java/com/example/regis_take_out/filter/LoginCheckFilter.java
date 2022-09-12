package com.example.regis_take_out.filter;

import com.alibaba.fastjson.JSON;
import com.example.regis_take_out.common.BaseContext;
import com.example.regis_take_out.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User：H11
 * Date：2022/8/16
 * Description：
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",  urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        log.info("拦截到请求：{}", request.getRequestURI());
        //1、获取本次请求的URI
        String uri = request.getRequestURI();
        //定义不需要处理的路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                //backend/**会忽略/backend/index.html，所以需要用到路径匹配器
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };
        //2、判断本次请求是否需要处理
        if(check(urls, uri)){
            filterChain.doFilter(request, response);
            return;
        }
        //3.1、判断employee登录状态，若已登录，直接放行
        if(request.getSession().getAttribute("employee") != null) {
            BaseContext.setCurrentId((Long) request.getSession().getAttribute("employee"));
            filterChain.doFilter(request, response);
            return;
        }
        //3.2、判断user登录状态，若已登录，直接放行
        if(request.getSession().getAttribute("user") != null) {
            BaseContext.setCurrentId((Long) request.getSession().getAttribute("user"));
            filterChain.doFilter(request, response);
            return;
        }
        //4、未登录，通过输出流的方式向前端响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }
    //路径匹配，检查此次请求是否需要放行
    public boolean check(String[] urls, String uri){
        for (String url : urls) {
            if(PATH_MATCHER.match(url, uri)) return true;
        }
        return false;
    }
}
