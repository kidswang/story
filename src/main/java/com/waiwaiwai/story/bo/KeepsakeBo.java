package com.waiwaiwai.story.bo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KeepsakeBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 发生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate happenDate;

    /**
     * 内容
     */
    private String message;

    /**
     * 用户id
     */
    private String userId;


}
