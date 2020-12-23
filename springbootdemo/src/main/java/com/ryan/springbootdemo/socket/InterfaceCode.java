package com.ryan.springbootdemo.socket;

/**
 * 类描述：交易枚举
 *
 * @author guankai
 * @date 2020/11/30
 **/
public enum InterfaceCode {

    //测试交易
    TEST(100001,"com.demo.springbootdemo.socket.service.TestServiceImpl","测试");





    /** 请求交易码 */
    private Integer reqCode;

    /** 交易处理类 */
    private String className;

    /** 类说明 */
    private String desc;

    public Integer getReqCode() {
        return reqCode;
    }

    public String getClassName() {
        return className;
    }

    public String getDesc() {
        return desc;
    }

    InterfaceCode(Integer reqCode, String className, String desc) {
        this.reqCode = reqCode;
        this.className = className;
        this.desc = desc;
    }

}
