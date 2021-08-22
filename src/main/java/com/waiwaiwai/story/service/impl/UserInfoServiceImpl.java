package com.waiwaiwai.story.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waiwaiwai.story.bo.UserInfoBo;
import com.waiwaiwai.story.pojo.UserInfo;
import com.waiwaiwai.story.mapper.UserInfoMapper;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import com.waiwaiwai.story.service.UserInfoService;
import com.waiwaiwai.story.util.HttpClientUtils;
import com.waiwaiwai.story.util.HttpExecuteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final String AUTH_CODE_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${wx.appid}")
    private String appId;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grant_type}")
    private String grantType;

    @Override
    public ResponseBean<String> register(String code) {
        ResponseBean<String> responseBean = new ResponseBean<>();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("appid" , appId);
        stringStringHashMap.put("secret" , secret);
        stringStringHashMap.put("js_code" , code);
        stringStringHashMap.put("grant_type" , grantType);
        HttpExecuteResponse httpExecuteResponse = HttpClientUtils.doGet(AUTH_CODE_URL, stringStringHashMap);

        String responseAsString = httpExecuteResponse.getResponseAsString();
        JSONObject jsonObject = JSON.parseObject(responseAsString);

        String errcode = (String) jsonObject.get("errcode");
        if (Objects.nonNull(errcode) && !errcode.equals(0)) {
            responseBean.setRes("");
            responseBean.setCode(ResponseStatusEnum.ERROR.getStatusCode());
            responseBean.setMsg(ResponseStatusEnum.ERROR.getMsg());
            return responseBean;
        }

        String openid = (String) jsonObject.get("openid");
        String sessionKey = (String) jsonObject.get("session_key");

        responseBean.setRes(openid);
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        return responseBean;
    }

    @Override
    public ResponseBean<String> saveOrUpdate(UserInfoBo userInfoBo) {
        ResponseBean<String> responseBean = new ResponseBean<>();
        String openId = userInfoBo.getOpenId();
        String nickName = userInfoBo.getNickName();
        LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();

        userInfoLambdaQueryWrapper.eq(UserInfo::getOpenId, openId);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoLambdaQueryWrapper);

        if (Objects.nonNull(userInfo)) {
            userInfo.setNickName(nickName);
            userInfo.setAvatarUrl(userInfoBo.getAvatarUrl());
            userInfoMapper.updateById(userInfo);
            responseBean.setRes(userInfo.getId());
            responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
            responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
            return responseBean;
        }

        userInfo = new UserInfo();

        userInfo.setNickName(nickName);
        userInfo.setAvatarUrl(userInfoBo.getAvatarUrl());
        userInfo.setGender(userInfoBo.getGender());
        userInfo.setOpenId(openId);
        userInfo.setGmtCreate(LocalDateTime.now());
        userInfo.setGmtModify(LocalDateTime.now());
        userInfo.setCountry(userInfoBo.getCountry());
        userInfo.setProvince(userInfoBo.getProvince());
        userInfo.setCity(userInfoBo.getCity());
        userInfoMapper.insert(userInfo);

        responseBean.setRes(userInfo.getId().toString());
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        return responseBean;
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return userInfoMapper.selectList(userInfoLambdaQueryWrapper);
    }
}
