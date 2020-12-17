package com.ryan.springbootdemo.aop;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自定义测试日志注解
 *
 * @author guankai
 * @date 2020/8/11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestLog {

    /** 描述 */
    @AliasFor("desc")
    String value() default "";

    /** 描述 */
    @AliasFor("value")
    String desc() default "";

    /** 是否记录参数 */
    boolean isRecordParam() default true;

}
