package com.waiwaiwai.story.pojo;


import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
