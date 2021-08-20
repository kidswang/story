package com.waiwaiwai.story.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;

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
@TableName("note_img")
public class NoteImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 记事本id
     */
    @TableField("note_id")
    private String noteId;

    /**
     * 图片地址
     */
    @TableField("url")
    private String url;

    /**
     * 图片名称
     */
    @TableField("name")
    private String name;

    /**
     * 图片md5
     */
    @TableField("md5")
    private String md5;

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

    public static final String NOTE_ID = "note_id";

    public static final String IMG_URL = "img_url";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFY = "gmt_modify";

}
