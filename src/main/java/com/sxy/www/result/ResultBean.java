package com.sxy.www.result;

/**
 * Created by user on 2018/9/17.
 */
public class ResultBean<T> {

    T data;
    boolean status;
    String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
