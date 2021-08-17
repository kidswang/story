package com.waiwaiwai.story.controller;


import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/register")
    public ResponseBean<String> register(@RequestParam String code) {
        return userInfoService.register(code);


    }


}
