package com.waiwaiwai.story.response;

import lombok.Getter;

/**
 * @author mhwangzl
 * @date 2021/8/17 14:24
 * @description
 */
public enum  ResponseStatusEnum {
    /**
     *
     */
    SUCCESS(200, "请求成功"),
    ERROR(500, "请求失败");

    @Getter
    private final int statusCode;

    @Getter
    private final String msg;

    ResponseStatusEnum(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

}
