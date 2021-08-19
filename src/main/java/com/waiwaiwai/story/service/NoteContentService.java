package com.waiwaiwai.story.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.waiwaiwai.story.bo.NoteContentBo;
import com.waiwaiwai.story.pojo.NoteContent;
import com.waiwaiwai.story.response.ResponseBean;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
public interface NoteContentService extends IService<NoteContent> {

    ResponseBean<Boolean> saveContent(NoteContentBo noteContentBo);
}
