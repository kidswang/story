package com.waiwaiwai.story.service;

import com.waiwaiwai.story.pojo.UserInfo;
import com.baomidou.mybatisplus.service.IService;
import com.waiwaiwai.story.response.ResponseBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
public interface UserInfoService extends IService<UserInfo> {

    ResponseBean<String> register(String code);
}
