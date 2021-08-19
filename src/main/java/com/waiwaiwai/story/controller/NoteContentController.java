package com.waiwaiwai.story.controller;


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

        return noteContentService.saveContent(noteContentBo);

    }

}
