package com.example.demo.model;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 */
@Data
public class Msg {
    //状态码。
    private int code;
    //提示信息
    private String msg;
    //用户要返回的数据
    private Map<String,Object> extend=new HashMap<String,Object>();

    public static Msg success(){
        Msg result= new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }
    public static Msg fail(){
        Msg result= new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key,value);
        return this;
    }
}
