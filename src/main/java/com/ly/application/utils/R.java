package com.ly.application.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class R<T> {

    private int code;

    private String msg;

    private T data;


    public static <T> R success(int code, String msg, T data) {
        return new R(code, msg, data);
    }

    public static <T> R success() {
        return new R(200, "操作成功", null);
    }

    public static <T> R success(T data) {
        return success(200, "操作成功", data);
    }

    public static <T> R success(T data, String msg) {
        return success(200, msg, data);
    }

    public static <T> R error(int code, String msg) {
        return new R(code, msg, null);
    }

    public static <T> R error(String msg) {
        return new R(500, msg, null);
    }

}
