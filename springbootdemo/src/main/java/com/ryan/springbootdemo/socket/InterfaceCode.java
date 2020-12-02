package com.ryan.springbootdemo.socket;

/**
 * 类描述：TODO
 *
 * @author guankai
 * @date 2020/11/30
 **/
public enum InterfaceCode {

    TEST(100001,"com.demo.springbootdemo.socket.service.TestServiceImpl");



    public Integer reqCode;

    public String className;


    InterfaceCode(Integer reqCode, String className) {
        this.reqCode = reqCode;
        this.className = className;
    }
}
