package com.crm.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统|一json返回值封装对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnObject<T> {
    private int code;
    private String msg;
    private T result;
    public static ReturnObject SUCCESS(){
        return new ReturnObject(0, "操作成功", null);
    }
    public static ReturnObject SUCCESS(Object result){
        return new ReturnObject(0, "操作成功", result);
    }
    public static ReturnObject ERROR(){
        return new ReturnObject(1, "操作失败", null);
    }
    public static ReturnObject ERROR(int code,String msg){
        return new ReturnObject(code, msg, null);


    }


}
