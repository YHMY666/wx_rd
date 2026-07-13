package edu.fdzc.wx1.controller;


import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;
    //提示信息
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    //成功响应
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }
    //失败响应
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }
}