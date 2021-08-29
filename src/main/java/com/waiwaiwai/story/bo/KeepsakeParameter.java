package com.waiwaiwai.story.bo;

import lombok.Data;

@Data
public class KeepsakeParameter {

    private String id;

    /**
     * 发生日期
     */
    private String happenDate;

    /**
     * 内容
     */
    private String message;

    /**
     * 用户id
     */
    private String userId;

}
