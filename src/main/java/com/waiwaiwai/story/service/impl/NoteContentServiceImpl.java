package com.waiwaiwai.story.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waiwaiwai.story.bo.ContentInfoBo;
import com.waiwaiwai.story.bo.ContentInfoParameter;
import com.waiwaiwai.story.bo.ContentInfoReturn;
import com.waiwaiwai.story.bo.NoteContentBo;
import com.waiwaiwai.story.mapper.NoteImgMapper;
import com.waiwaiwai.story.pojo.NoteContent;
import com.waiwaiwai.story.mapper.NoteContentMapper;
import com.waiwaiwai.story.pojo.NoteImg;
import com.waiwaiwai.story.pojo.UserInfo;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import com.waiwaiwai.story.service.NoteContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waiwaiwai.story.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@Service
public class NoteContentServiceImpl extends ServiceImpl<NoteContentMapper, NoteContent> implements NoteContentService {

    @Autowired
    private NoteContentMapper noteContentMapper;
    @Autowired
    private NoteImgMapper noteImgMapper;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public ResponseBean<Boolean> saveContent(NoteContentBo noteContentBo, String userId) {
        ResponseBean<Boolean> responseBean = new ResponseBean<>();
        // 保存主表
        String happenDate = noteContentBo.getHappenDate();
        LocalDate date = LocalDate.now();
        if (StringUtils.isNotBlank(happenDate)) {
            date = LocalDate.parse(happenDate);
        }

        NoteContent noteContent = new NoteContent();
        noteContent.setContent(noteContentBo.getContent());
        noteContent.setUserId(noteContentBo.getUserId());
        noteContent.setHappenDate(date);
        noteContent.setGmtCreate(LocalDateTime.now());
        noteContent.setGmtModify(LocalDateTime.now());
        noteContentMapper.insert(noteContent);
        // 保存图片
        NoteImg noteImg = new NoteImg();
        noteImg.setNoteId(noteContent.getId());
        noteImg.setUrl(noteContentBo.getUrl());
        noteImg.setMd5(noteContentBo.getMd5());
        noteImg.setGmtCreate(LocalDateTime.now());
        noteImg.setGmtModify(LocalDateTime.now());
        noteImgMapper.insert(noteImg);

        responseBean.setRes(Boolean.TRUE);
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());

        return responseBean;
    }

    @Override
    public ResponseBean<ContentInfoReturn> listContentInfo(ContentInfoParameter contentInfoParameter, String userId) {
        ResponseBean<ContentInfoReturn> responseBean = new ResponseBean<>();
        ContentInfoReturn contentInfoReturn = new ContentInfoReturn();
        List<ContentInfoBo> contentInfoBos = new ArrayList<>();
        Integer page = contentInfoParameter.getPage();
        Integer size = contentInfoParameter.getSize();

        LambdaQueryWrapper<NoteContent> noteContentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        IPage<NoteContent> page1 = new Page<>(page, size);
        IPage<NoteContent> noteContentIPage = noteContentMapper.selectPage(page1, noteContentLambdaQueryWrapper);
        Long total = noteContentIPage.getTotal();
        contentInfoReturn.setTotalCount(total);

        List<NoteContent> records = noteContentIPage.getRecords();
        if (CollUtil.isEmpty(records)) {
            responseBean.setRes(contentInfoReturn);
            responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
            responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
            return responseBean;
        }

        List<String> collect = records.stream().map(NoteContent::getId).collect(Collectors.toList());
        LambdaQueryWrapper<NoteImg> noteImgLambdaQueryWrapper = new LambdaQueryWrapper<>();
        noteImgLambdaQueryWrapper.in(NoteImg::getNoteId, collect);
        List<NoteImg> noteImgs = noteImgMapper.selectList(noteImgLambdaQueryWrapper);
        HashMap<String, String> noteImgsMap = noteImgs.stream().collect(HashMap::new, (m, v) -> m.put(v.getNoteId(), v.getUrl()), HashMap::putAll);

        List<UserInfo> allUserInfo = userInfoService.getAllUserInfo();
        HashMap<String, UserInfo> allUserInfoMap = allUserInfo.stream().collect(HashMap::new, (m, v) -> m.put(v.getId(), v), HashMap::putAll);

        for (NoteContent record : records) {
            String id = record.getId();

            UserInfo userInfo = allUserInfoMap.get(record.getUserId());

            ContentInfoBo contentInfoBo = new ContentInfoBo();
            contentInfoBo.setId(id);
            if (Objects.nonNull(userInfo)) {
                contentInfoBo.setNickName(userInfo.getNickName());
                contentInfoBo.setAvatarUrl(userInfo.getAvatarUrl());
            }
            contentInfoBo.setContent(record.getContent());
            if (Objects.nonNull(record.getHappenDate())) {
                contentInfoBo.setHappenDate(record.getHappenDate().toString());
            }
            String noteImgUrl = noteImgsMap.get(id);
            if (StringUtils.isNotBlank(noteImgUrl)) {
                contentInfoBo.setUrl(noteImgUrl);
            }

            contentInfoBos.add(contentInfoBo);
        }
        contentInfoReturn.setContentInfoBos(contentInfoBos);

        responseBean.setRes(contentInfoReturn);
        responseBean.setMsg(ResponseStatusEnum.SUCCESS.getMsg());
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        return responseBean;
    }
}
