package com.waiwaiwai.story.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("note_content")
public class NoteContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发生日期
     */
    @TableField("happen_date")
    private LocalDateTime happenDate;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

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


    public static final String ID = "id";

    public static final String HAPPEN_DATE = "happen_date";

    public static final String CONTENT = "content";

    public static final String USER_ID = "user_id";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFY = "gmt_modify";

}
