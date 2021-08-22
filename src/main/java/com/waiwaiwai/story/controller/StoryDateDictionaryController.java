package com.waiwaiwai.story.controller;


import com.waiwaiwai.story.pojo.StoryDateDictionary;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.service.StoryDateDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/storyDateDictionary")
public class StoryDateDictionaryController {

    @Autowired
    private StoryDateDictionaryService storyDateDictionaryService;

    @GetMapping(value = "/getStoryDate")
    public ResponseBean<StoryDateDictionary> getStoryDate() {
        return storyDateDictionaryService.getStoryDate();

    }



}
