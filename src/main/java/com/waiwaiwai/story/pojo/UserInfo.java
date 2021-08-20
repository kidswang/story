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
@TableName("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户名
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 微信头像地址
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 性别 1男2女
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 国家
     */
    @TableField("country")
    private String country;

    /**
     * 省份
     */
    @TableField("province")
    private String province;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 微信开放id
     */
    @TableField("open_id")
    private String openId;

    /**
     * 微信联合id
     */
    @TableField("union_id")
    private String unionId;

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
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 身份证号
     */
    @TableField("id_card_no")
    private String idCardNo;

    /**
     * 会话秘钥
     */
    @TableField("session_key")
    private String sessionKey;


    public static final String ID = "id";

    public static final String NICK_NAME = "nick_name";

    public static final String AVATAR_URL = "avatar_url";

    public static final String GENDER = "gender";

    public static final String COUNTRY = "country";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String OPEN_ID = "open_id";

    public static final String UNION_ID = "union_id";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFY = "gmt_modify";

    public static final String MOBILE = "mobile";

    public static final String ID_CARD_NO = "id_card_no";

    public static final String SESSION_KEY = "session_key";

}
