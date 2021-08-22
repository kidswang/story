package com.waiwaiwai.story.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waiwaiwai.story.pojo.StoryDateDictionary;
import com.waiwaiwai.story.mapper.StoryDateDictionaryMapper;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import com.waiwaiwai.story.service.StoryDateDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@Service
public class StoryDateDictionaryServiceImpl extends ServiceImpl<StoryDateDictionaryMapper, StoryDateDictionary> implements StoryDateDictionaryService {

    @Autowired
    private StoryDateDictionaryMapper storyDateDictionaryMapper;

    @Override
    public ResponseBean<StoryDateDictionary> getStoryDate() {
        ResponseBean<StoryDateDictionary> responseBean = new ResponseBean<>();

//        LambdaQueryWrapper<StoryDateDictionary> storyDateDictionaryLambdaQueryWrapper = new LambdaQueryWrapper<>();

        StoryDateDictionary storyDateDictionary = storyDateDictionaryMapper.selectById("1");
        responseBean.setRes(storyDateDictionary);
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        return responseBean;
    }

}
