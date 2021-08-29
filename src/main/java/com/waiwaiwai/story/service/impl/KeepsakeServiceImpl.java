package com.waiwaiwai.story.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waiwaiwai.story.bo.KeepsakeBo;
import com.waiwaiwai.story.bo.KeepsakeParameter;
import com.waiwaiwai.story.mapper.KeepsakeMapper;
import com.waiwaiwai.story.pojo.Keepsake;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import com.waiwaiwai.story.service.KeepsakeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class KeepsakeServiceImpl extends ServiceImpl<KeepsakeMapper, Keepsake> implements KeepsakeService {

    @Autowired
    private KeepsakeMapper keepsakeMapper;

    @Override
    public ResponseBean<List<KeepsakeBo>> listAll() {
        ResponseBean<List<KeepsakeBo>> responseBean = new ResponseBean<>();
        List<KeepsakeBo> list = new ArrayList<>();
        LambdaQueryWrapper<Keepsake> keepsakeBoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Keepsake> keepsakes = keepsakeMapper.selectList(keepsakeBoLambdaQueryWrapper);
        for (Keepsake keepsake : keepsakes) {
            KeepsakeBo keepsakeBo = new KeepsakeBo();
            keepsakeBo.setId(keepsake.getId());
            keepsakeBo.setHappenDate(keepsake.getHappenDate());
            keepsakeBo.setMessage(keepsake.getMessage());
//            keepsakeBo.setUserId(keepsake.getUserId());
            list.add(keepsakeBo);
        }
        responseBean.setRes(list);
        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());

        return responseBean;
    }

    @Override
    public ResponseBean<Boolean> addInfo(KeepsakeParameter keepsakeParameter, String userId) {
        ResponseBean<Boolean> responseBean = new ResponseBean<>();
        if (Objects.isNull(keepsakeParameter)) {
            responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
            responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
            responseBean.setRes(Boolean.FALSE);
            return responseBean;
        }

        String id = keepsakeParameter.getId();
        Keepsake keepsake;
        LocalDate date;
        if (StringUtils.isNotBlank(id)) {
            keepsake = keepsakeMapper.selectById(id);
            date = keepsake.getHappenDate();
            String happenDate = keepsakeParameter.getHappenDate();
            if (StringUtils.isNotBlank(happenDate)) {
                date = LocalDate.parse(happenDate);
            }
        } else {
            keepsake = new Keepsake();
            date = LocalDate.now();
            String happenDate = keepsakeParameter.getHappenDate();
            if (StringUtils.isNotBlank(happenDate)) {
                date = LocalDate.parse(happenDate);
            }
            keepsake.setGmtCreate(LocalDateTime.now());
        }
        keepsake.setHappenDate(date);
        keepsake.setMessage(keepsakeParameter.getMessage());
        keepsake.setUserId(userId);
        keepsake.setGmtModify(LocalDateTime.now());
        this.saveOrUpdate(keepsake);

        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setRes(Boolean.TRUE);
        return responseBean;
    }

    @Override
    public ResponseBean<Boolean> deleteInfo(String id) {
        ResponseBean<Boolean> responseBean = new ResponseBean<>();
        int i = keepsakeMapper.deleteById(id);

        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setRes(Boolean.TRUE);
        return responseBean;
    }

    @Override
    public ResponseBean<Boolean> modifyInfo(KeepsakeParameter keepsakeParameter, String userId) {
        ResponseBean<Boolean> responseBean = new ResponseBean<>();
        if (Objects.isNull(keepsakeParameter) && Objects.isNull(keepsakeParameter.getId())) {
            responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
            responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
            responseBean.setRes(Boolean.FALSE);
            return responseBean;
        }
        Keepsake keepsake = new Keepsake();
        LocalDate date = null;
        String happenDate = keepsakeParameter.getHappenDate();
        if (StringUtils.isNotBlank(happenDate)) {
            date = LocalDate.parse(happenDate);
        }
//        Keepsake keepsake1 = keepsakeMapper.selectById(keepsakeParameter.getId());
        keepsake.setId(keepsakeParameter.getId());
        keepsake.setHappenDate(date);
        keepsake.setMessage(keepsakeParameter.getMessage());
        keepsake.setUserId(userId);
//        keepsake.setGmtCreate(LocalDateTime.now());
        keepsake.setGmtModify(LocalDateTime.now());
        keepsakeMapper.updateById(keepsake);

        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setRes(Boolean.TRUE);
        return responseBean;
    }

    @Override
    public ResponseBean<KeepsakeBo> selectById(String id) {
        ResponseBean<KeepsakeBo> responseBean = new ResponseBean<>();
        KeepsakeBo keepsakeBo = new KeepsakeBo();

        Keepsake keepsake = keepsakeMapper.selectById(id);
        BeanUtils.copyProperties(keepsake, keepsakeBo);

        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setRes(keepsakeBo);
        return responseBean;
    }
}
