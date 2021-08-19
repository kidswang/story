package com.waiwaiwai.story.service;

import com.waiwaiwai.story.bo.UserInfoBo;
import com.waiwaiwai.story.pojo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
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

    /**
     * 注册返回openId
     * @param code
     * @return
     */
    ResponseBean<String> register(String code);

    /**
     * 保存或更新姓名
     * @param userInfoBo
     * @return
     */
    ResponseBean<String> saveOrUpdate(UserInfoBo userInfoBo);
}
