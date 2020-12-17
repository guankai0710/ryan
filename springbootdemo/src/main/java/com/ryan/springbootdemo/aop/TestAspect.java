package com.ryan.springbootdemo.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试aop
 *
 * @author guankai
 * @date 2020/8/10
 **/
@Component
@Aspect
public class TestAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAspect.class);

    /**
     * 切点
     */
    @Pointcut("@annotation(com.ryan.springbootdemo.aop.TestLog)")
    public void parseAnnotation(){
    }

    /**
     * 方法执行前切入
     * @param joinPoint
     */

    @Before(value = "parseAnnotation()")
    public void toDoSomething(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 方法执行后返回时切入
     * @param joinPoint
     * @param testLog
     * @param resultVal
     */
    @AfterReturning(returning = "resultVal",pointcut = "parseAnnotation()&&@annotation(testLog)")
    public void recordLog(JoinPoint joinPoint, TestLog testLog, Object resultVal){
        //注解描述
        String desc = testLog.value();
        //获取方法全名
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName()+"$"+signature.getName();
        String parames = "";
        if (testLog.isRecordParam()){
            //获取方法入参值
            Object[] args = joinPoint.getArgs();
            //获取方法入参名
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            Map<String, Object> paramMap = new HashMap<>(parameterNames.length+1);
            for (int i=0;i<parameterNames.length;i++){
                paramMap.put(parameterNames[i],args[i]);
            }
            parames = JSON.toJSONString(paramMap);
        }

        Map<String, String> map = new HashMap<>(3);
        map.put("desc", desc);
        map.put("methodName", methodName);
        map.put("parames", parames);
        map.put("valLog", JSON.toJSONString(resultVal));

        LOGGER.info("打印：{}", JSON.toJSONString(map));

    }

}
