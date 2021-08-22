package com.waiwaiwai.story.controller;


import com.waiwaiwai.story.pojo.TrackModel;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.service.TrackModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangzhenglei
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/trackModel")
public class TrackModelController {

    @Autowired
    private TrackModelService trackModelService;

    @GetMapping(value = "/listAll")
    public ResponseBean<List<TrackModel>> listAll() {
        return trackModelService.listAll();
    }


}
