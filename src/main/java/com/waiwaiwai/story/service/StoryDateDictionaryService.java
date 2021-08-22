package com.waiwaiwai.story.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waiwaiwai.story.pojo.StoryDateDictionary;
import com.waiwaiwai.story.response.ResponseBean;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
public interface StoryDateDictionaryService extends IService<StoryDateDictionary> {

    ResponseBean<StoryDateDictionary> getStoryDate();

}
