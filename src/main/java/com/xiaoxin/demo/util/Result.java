package com.xiaoxin.demo.util;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName Result--统一的 REST响应对象
 * @createDate 2019/12/4 15:21
 */
public class Result {

    private static int SUCCESS_CODE = 0;
    private static int FAIL_CODE = 1;
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(SUCCESS_CODE, null, null);
    }

    public static Result success(Object data) {
        return new Result(SUCCESS_CODE, "", null);
    }

    public static Result fail(String message) {
        return new Result(FAIL_CODE, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
