package com.ryan.springbootdemo.config.intercepors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录、注册拦截器（在用户没有登录前，除登录、注册页面外，任何其他请求不能再地址栏直接访问）
 *
 * @author: guan.kai
 * @date: 2019/8/29 17:34
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 该方法将在请求处理之前进行调用，返回false时，整个请求结束
     *
     * @author guan.kai
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
//        HttpSession session = request.getSession();
//        //这里的Person是登陆时放入session的
//        Person user = (Person) session.getAttribute("person");
//        //如果session中没有user，表示没登陆
//        if (user == null){
//            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
//            //用户没登录自动重定向到登陆页面
//            response.sendRedirect("/login");
//            return false;
//        }else {
//            //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
//            return true;
//        }
        return true;
    }

    /**
     * 该方法将在preHandle方法返回true时进行调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 该方法将在preHandle方法返回true时进行调用
     * 该方法将在整个请求结束之后调用
     * 主要作用是用于进行资源清理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
