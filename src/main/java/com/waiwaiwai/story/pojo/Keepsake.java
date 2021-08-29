package com.waiwaiwai.story.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("keepsake")
public class Keepsake implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 发生日期
     */
    @TableField("happen_date")
    private LocalDate happenDate;

    /**
     * 内容
     */
    @TableField("message")
    private String message;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @TableField("gmt_modify")
    private LocalDateTime gmtModify;

    /**
     * 更新时间
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;



    public static final String ID = "id";

    public static final String HAPPEN_DATE = "happen_date";

    public static final String MESSAGE = "message";

    public static final String USER_ID = "user_id";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFY = "gmt_modify";

}
