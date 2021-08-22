package com.waiwaiwai.story.service;

import com.waiwaiwai.story.pojo.TrackModel;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface TrackModelService extends IService<TrackModel> {

    ResponseBean<List<TrackModel>> listAll();
}
