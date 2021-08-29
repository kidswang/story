package com.waiwaiwai.story.controller;

import com.waiwaiwai.story.bo.KeepsakeBo;
import com.waiwaiwai.story.bo.KeepsakeParameter;
import com.waiwaiwai.story.pojo.Keepsake;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.service.KeepsakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/keepsake")
public class KeepsakeController {
    @Autowired
    private KeepsakeService keepsakeService;

    @GetMapping(value = "/listAll")
    public ResponseBean<List<KeepsakeBo>> listAll() {
        return keepsakeService.listAll();
    }

    @PostMapping(value = "/addInfo")
    public ResponseBean<Boolean> addInfo(@RequestBody KeepsakeParameter keepsakeParameter,  HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return keepsakeService.addInfo(keepsakeParameter, userId);
    }

    @GetMapping(value = "/deleteInfo")
    public ResponseBean<Boolean> deleteInfo(@RequestParam("id") String id) {
        return keepsakeService.deleteInfo(id);
    }

    @PostMapping(value = "/modifyInfo")
    public ResponseBean<Boolean> modifyInfo(@RequestBody KeepsakeParameter keepsakeParameter,  HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return keepsakeService.modifyInfo(keepsakeParameter, userId);
    }

    @GetMapping(value = "/selectById")
    public ResponseBean<KeepsakeBo> selectById(@RequestParam("id") String id) {
        return keepsakeService.selectById(id);
    }


}
