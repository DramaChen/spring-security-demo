package com.ncov.base.core.entity;



import java.io.Serializable;

public class JsonResultVO<T> implements Serializable {
    private static final long serialVersionUID = -5139171544599906474L;
    private int code;
    private String message;
    private T data;
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
    public static final int ERROR = -1;

    public JsonResultVO() {
    }

    private JsonResultVO(int code, String message) {
        this(code, message, null);
    }

    private JsonResultVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
//        ReflectUtils.checkObject(data);
        this.data = data;
    }

    public static <T> JsonResultVO<T> success(String message) {
        return new JsonResultVO<>(SUCCESS, message);
    }

    public static <T> JsonResultVO<T> success(T data) {
        return new JsonResultVO<>(SUCCESS, "成功", data);
    }

    public static <T> JsonResultVO<T> success(String message, T data) {
        return new JsonResultVO<>(SUCCESS, message, data);
    }

    public static <T> JsonResultVO<T> failure(String message) {
        return new JsonResultVO<>(FAILURE, message);
    }

    public static <T> JsonResultVO<T> error(String message) {
        return new JsonResultVO<>(ERROR, message);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
