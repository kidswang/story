package com.waiwaiwai.story.service.impl;

import com.waiwaiwai.story.pojo.UserInfo;
import com.waiwaiwai.story.mapper.UserInfoMapper;
import com.waiwaiwai.story.service.UserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
