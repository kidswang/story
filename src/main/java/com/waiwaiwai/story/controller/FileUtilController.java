package com.waiwaiwai.story.controller;

import com.waiwaiwai.story.file.biz.FileBiz;
import com.waiwaiwai.story.response.FileUploadResponse;
import com.waiwaiwai.story.response.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/store")
public class FileUtilController {

    @Autowired
    private FileBiz fileBiz;

    @PostMapping(value = "/upload")
    public ResponseBean<FileUploadResponse> upload(MultipartFile file, HttpServletRequest request) {
        String userId = request.getHeader("userId");

        return fileBiz.upload(file, userId);
    }


    @PostMapping(value = "/uploadImg")
    public ResponseBean<FileUploadResponse> uploadImg(MultipartFile file, HttpServletRequest request) throws IOException {
        String userId = request.getHeader("userId");

        return fileBiz.uploadImgByte(file.getBytes(), userId);
    }

}
