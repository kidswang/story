package com.waiwaiwai.story.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waiwaiwai.story.bo.KeepsakeBo;
import com.waiwaiwai.story.bo.KeepsakeParameter;
import com.waiwaiwai.story.pojo.Keepsake;
import com.waiwaiwai.story.response.ResponseBean;

import java.util.List;

public interface KeepsakeService extends IService<Keepsake> {
    ResponseBean<List<KeepsakeBo>> listAll();

    ResponseBean<Boolean> addInfo(KeepsakeParameter keepsakeParameter, String userId);

    ResponseBean<Boolean> deleteInfo(String id);

    ResponseBean<Boolean> modifyInfo(KeepsakeParameter keepsakeParameter, String userId);

    ResponseBean<KeepsakeBo> selectById(String id);
}
