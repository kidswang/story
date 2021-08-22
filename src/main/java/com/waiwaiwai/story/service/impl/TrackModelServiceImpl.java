package com.waiwaiwai.story.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.waiwaiwai.story.pojo.TrackModel;
import com.waiwaiwai.story.mapper.TrackModelMapper;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import com.waiwaiwai.story.service.TrackModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TrackModelServiceImpl extends ServiceImpl<TrackModelMapper, TrackModel> implements TrackModelService {

    @Autowired
    private TrackModelMapper trackModelMapper;

    @Override
    public ResponseBean<List<TrackModel>> listAll() {
        ResponseBean<List<TrackModel>> responseBean = new ResponseBean<>();
        LambdaQueryWrapper<TrackModel> trackModelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        trackModelLambdaQueryWrapper.orderByAsc(TrackModel::getItemId);
        List<TrackModel> trackModels = trackModelMapper.selectList(trackModelLambdaQueryWrapper);
        responseBean.setRes(trackModels);
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        return responseBean;
    }
}
