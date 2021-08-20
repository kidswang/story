package com.waiwaiwai.story.controller;


import com.waiwaiwai.story.bo.ContentInfoBo;
import com.waiwaiwai.story.bo.ContentInfoParameter;
import com.waiwaiwai.story.bo.ContentInfoReturn;
import com.waiwaiwai.story.bo.NoteContentBo;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.service.NoteContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/noteContent")
public class NoteContentController {

    @Autowired
    private NoteContentService noteContentService;

    @PostMapping(value = "/saveContent")
    public ResponseBean<Boolean> saveContent(@RequestBody NoteContentBo noteContentBo, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return noteContentService.saveContent(noteContentBo, userId);
    }

    @PostMapping(value = "/listContentInfo")
    public ResponseBean<ContentInfoReturn> listContentInfo(@RequestBody ContentInfoParameter contentInfoParameter, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return noteContentService.listContentInfo(contentInfoParameter, userId);
    }


}
