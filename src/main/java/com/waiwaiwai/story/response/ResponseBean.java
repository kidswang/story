package com.waiwaiwai.story.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author mhwangzl
 * @date 2021/8/17 14:23
 * @description
 */


@Data
public class ResponseBean<T> {
    private Integer code;
    private T res;
    private String msg;

    /**
     * 妥协 保留之前
     */
    public ResponseBean() {
        this.code = ResponseStatusEnum.SUCCESS.getStatusCode();
    }

    private ResponseBean(HttpStatus status) {
        this.code = status.value();
    }

    private ResponseBean(Integer status) {
        this.code = status;
    }

    private ResponseBean(HttpStatus status, String msg) {
        this.code = status.value();
        this.msg = msg;
    }

    private ResponseBean(Integer status, String msg) {
        this.code = status;
        this.msg = msg;
    }

    private ResponseBean(HttpStatus status, String msg, T res) {
        this.code = status.value();
        this.msg = msg;
        this.res = res;
    }

    private ResponseBean(Integer status, String msg, T res) {
        this.code = status;
        this.msg = msg;
        this.res = res;
    }

    public static <T> ResponseBean<T> ok() {
        return new ResponseBean<>(HttpStatus.OK);
    }

    public static <T> ResponseBean<T> ok(T body) {
        return ok(HttpStatus.OK, body);
    }

    public static <T> ResponseBean<T> ok(String msg, T body) {
        return ok(HttpStatus.OK, msg, body);
    }

    public static <T> ResponseBean<T> ok(HttpStatus status, T body) {
        return ok(status, (String) null, body);
    }

    public static <T> ResponseBean<T> ok(HttpStatus status, String msg, T body) {
        return new ResponseBean<>(status, msg, body);
    }

    public static <T> ResponseBean<T> ok(Integer status, T body) {
        return new ResponseBean<>(status, (String) null, body);
    }

    public static <T> ResponseBean<T> failed(HttpStatus status) {
        return new ResponseBean<>(status);
    }

    public static <T> ResponseBean<T> failed(HttpStatus status, String msg) {
        return new ResponseBean<>(status, msg);
    }

    public static <T> ResponseBean<T> failed(HttpStatus status, String msg, T body) {
        return new ResponseBean<>(status, msg, body);
    }

    public static <T> ResponseBean<T> failed(Integer status) {
        return new ResponseBean<>(status);
    }

    public static <T> ResponseBean<T> failed(Integer status, String msg) {
        return new ResponseBean<>(status, msg);
    }

    public static <T> ResponseBean<T> failed(Integer status, String msg, T body) {
        return new ResponseBean<>(status, msg, body);
    }


    @Override
    public String toString() {
        return "ResponseBean{code=" + this.code + ", res=" + this.res + ", msg='" + this.msg + "'}";
    }

}
