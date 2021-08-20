package com.waiwaiwai.story.controller;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.waiwaiwai.story.file.biz.FileBiz;
import com.waiwaiwai.story.response.FileUploadResponse;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/store")
public class FileUtilController {

    @Value("${go-fastdfs.uploadUrl}")
    private String uploadUrl;
    @Value("${go-fastdfs.deleteUrl}")
    private String deleteUrl;

    @PostMapping("/upload")
    public ResponseBean<FileUploadResponse> upload(MultipartFile file, HttpServletRequest request) {
        try {
            ResponseBean<FileUploadResponse> responseBean = new ResponseBean<>();
            LocalDateTime now = LocalDateTime.now();
            String userId = "0";
            if (request != null) {
                userId = request.getParameter("userId");
            }
            InputStreamResource isr = new InputStreamResource(file.getInputStream(),
                    file.getOriginalFilename());
            Map<String, Object> params = new HashMap<>(3);
            params.put("file", isr);
            params.put("path", userId + "/" + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth());
            params.put("output", "json");
            String resp = HttpUtil.post(uploadUrl, params);
            JSONObject hashMap = JSON.parseObject(resp, JSONObject.class);
            FileUploadResponse fileUploadResponse = new FileUploadResponse();
            fileUploadResponse.setName(file.getOriginalFilename());
            fileUploadResponse.setUrl(hashMap.get("src").toString());
            fileUploadResponse.setType(Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")));
            fileUploadResponse.setSize(hashMap.get("size").toString());
            fileUploadResponse.setMd5(hashMap.get("md5").toString());
            responseBean.setRes(fileUploadResponse);
            responseBean.setMsg("上传成功");
            responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
            return responseBean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete")
    public ResponseBean<Null> deleteFile(String md5) {
        ResponseBean<Null> responseBean = new ResponseBean<>();
        if (StringUtils.isEmpty(md5)) {
            responseBean.setCode(500);
            responseBean.setMsg("md5为空");
            return responseBean;
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("md5", md5);
            String post = HttpUtil.post(deleteUrl, params);
            if (post.contains("success")) {
                responseBean.setCode(200);
                responseBean.setMsg("删除成功");
                return responseBean;
            }
            System.out.println(post);
        } catch (Exception e) {
            e.getMessage();
        }
        responseBean.setCode(500);
        responseBean.setMsg("删除失败");
        return responseBean;
    }

    @PostMapping("/uploadImg")
    public ResponseBean<String> uploadImg(String base64, HttpServletRequest request) throws IOException {
        byte[] bytes = Base64.decodeBase64(base64);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        MultipartFile file = new MockMultipartFile("file", UUID.randomUUID().toString()+".jpg", "multipart/form-data; charset=ISO-8859-1", in);
        ResponseBean<FileUploadResponse> fileUploadResponseResponseBean = this.upload(file, request);
        in.close();
        String url=fileUploadResponseResponseBean.getRes().getUrl();
        ResponseBean<String> responseBean=new ResponseBean<>();
        responseBean.setRes(url);
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setMsg("上传成功");
        return responseBean;
    }

}
