package com.ryan.springbootdemo.socket.service;

import com.alibaba.fastjson.JSON;
import com.ryan.springbootdemo.socket.request.TestRequset;
import com.ryan.springbootdemo.vo.Result;

/**
 * 类描述：TODO
 *
 * @author guankai
 * @date 2020/11/30
 **/
public class TestServiceImpl extends BaseServiceImpl {

    @Override
    public String execute(String reqJson) {
        TestRequset testRequset = JSON.parseObject(reqJson,TestRequset.class);
        return toDo(testRequset);
    }

    private String toDo(TestRequset requset){

        return JSON.toJSONString(Result.success("11111111111"));
    }
}
