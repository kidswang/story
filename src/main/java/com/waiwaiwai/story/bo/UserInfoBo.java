package com.waiwaiwai.story.bo;

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

}
