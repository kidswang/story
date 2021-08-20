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
@TableName("story_date_dictionary")
public class StoryDateDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 恋爱开始日期
     */
    @TableField("love_begin_date")
    private LocalDateTime loveBeginDate;

    /**
     * 结婚开始日期
     */
    @TableField("marry_love_date")
    private LocalDateTime marryLoveDate;


    public static final String ID = "id";

    public static final String LOVE_BEGIN_DATE = "love_begin_date";

    public static final String MARRY_LOVE_DATE = "marry_love_date";

}
