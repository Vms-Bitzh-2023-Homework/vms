package com.zhbit.common;

import lombok.Data;

@Data
public class Result {

    private int statusCode;
    private String msg;
    private Object data;
    private String token;

    public Result() {
    }

    public Result(int statusCode, String msg, Object data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public Result(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public Result(int statusCode, String msg, String token) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.token = token;
    }
}
