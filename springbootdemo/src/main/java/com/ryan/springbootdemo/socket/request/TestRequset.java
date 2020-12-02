package com.ryan.springbootdemo.socket.request;

/**
 * 类描述：TODO
 *
 * @author guankai
 * @date 2020/11/30
 **/
public class TestRequset extends BaseRequest {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
