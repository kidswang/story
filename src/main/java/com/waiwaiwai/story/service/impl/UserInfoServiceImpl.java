package com.waiwaiwai.story.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.waiwaiwai.story.pojo.UserInfo;
import com.waiwaiwai.story.mapper.UserInfoMapper;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import com.waiwaiwai.story.service.UserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.waiwaiwai.story.util.HttpClientUtils;
import com.waiwaiwai.story.util.HttpExecuteResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
        StringBuilder builder = new StringBuilder();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("appid" , appId);
        stringStringHashMap.put("secret" , secret);
        stringStringHashMap.put("js_code" , code);
        stringStringHashMap.put("grant_type" , grantType);
        HttpExecuteResponse httpExecuteResponse = HttpClientUtils.doGet(AUTH_CODE_URL, stringStringHashMap);

        String responseAsString = httpExecuteResponse.getResponseAsString();
        JSONObject jsonObject = JSON.parseObject(responseAsString);

        String errcode = (String) jsonObject.get("errcode");
        if (!errcode.equals(0)) {
            responseBean.setRes("");
            responseBean.setCode(ResponseStatusEnum.ERROR.getStatusCode());
            responseBean.setMsg(ResponseStatusEnum.ERROR.getMsg());
            return responseBean;
        }

        String openid = (String) jsonObject.get("openid");
        String sessionKey = (String) jsonObject.get("session_key");




        return null;
    }
}
