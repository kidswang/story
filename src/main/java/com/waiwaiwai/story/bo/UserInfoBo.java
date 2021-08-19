package com.waiwaiwai.story.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserInfoBo {


    /**
     * 用户名
     */
    private String nickName;

    /**
     * 微信头像地址
     */
    private String avatarUrl;

    /**
     * 性别 1男2女
     */
    private Integer gender;

    /**
     * 微信开放id
     */
    private String openId;


    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

}
