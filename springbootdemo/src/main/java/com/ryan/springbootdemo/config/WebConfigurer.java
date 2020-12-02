package com.ryan.springbootdemo.config;

import com.ryan.springbootdemo.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * web拦截器配置类
 *
 * @author: guan.kai
 * @date: 2019/8/29 17:31
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    /** 登录拦截器 */
    @Autowired
    private LoginInterceptor loginInterceptor;


    /** 放行路径 */
    private static final List<String> EXCLUDE_PATHS = new ArrayList<>();

    static {
        /** 静态资源路径 */
        EXCLUDE_PATHS.addAll(Arrays.asList("/css/**", "/js/**", "/images/**", "/fonts/**", "/bootstrap/**"));

        /** 接口路径 */
        EXCLUDE_PATHS.add("/test");
        EXCLUDE_PATHS.add("/test/post");
        EXCLUDE_PATHS.add("/login");
        EXCLUDE_PATHS.add("/register");
    }



    /**
     * 注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
//        super.addInterceptors(registry);    //较新Spring Boot的版本中这里可以直接去掉，否则会报错
    }

    /**
     * 配置静态资源，隐藏资源在服务器中的真实路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(this.url + "**")
//                .addResourceLocations("file:" + this.path);
        //addResourceHandler("/static/**")     //对外访问路径
        //addResourceLocations("classpath:/static/")    //资源真实路径
        //addResourceLocations("file:/work/")    //资源真实路径
    }
}
