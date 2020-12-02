package com.ryan.springbootdemo.vo;

import com.ryan.springbootdemo.enumeration.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回实体
 *
 * @author guankai
 * @date 2020/11/4
 **/
@Getter
@Setter
public class Result {

    /** 状态码 */
    private Integer code;

    /** 信息 */
    private String msg;

    /** 数据 */
    private Object data;

    public Result() { }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public static Result success(){
        return new Result(ResultCode.SUCCESS, null);
    }

    public static Result success(Object data){
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result failure(ResultCode resultCode){
        return new Result(resultCode, null);
    }

    public static Result failure(ResultCode resultCode, Object data){
        return new Result(resultCode, data);
    }

}
