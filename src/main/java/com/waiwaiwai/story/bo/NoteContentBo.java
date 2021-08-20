package com.waiwaiwai.story.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.checkerframework.checker.formatter.qual.Format;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NoteContentBo {

    /**
     * 发生日期
     */
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String happenDate;

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
    private String url;

    /**
     * 图片md5
     */
    private String md5;


}
