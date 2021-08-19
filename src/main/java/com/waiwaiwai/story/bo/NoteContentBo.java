package com.waiwaiwai.story.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteContentBo {

    /**
     * 发生日期
     */
    private LocalDateTime happenDate;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片md5
     */
    private String md5;



}
