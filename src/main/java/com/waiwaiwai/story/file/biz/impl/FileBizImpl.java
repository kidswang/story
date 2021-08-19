package com.waiwaiwai.story.file.biz.impl;


import cn.hutool.http.HttpUtil;
import com.waiwaiwai.story.file.biz.FileBiz;
import com.waiwaiwai.story.response.FileUploadResponse;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.ResponseStatusEnum;
import com.waiwaiwai.story.response.VideoUploadResponse;
import com.waiwaiwai.story.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;


import javax.validation.constraints.Null;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class FileBizImpl implements FileBiz {

    @Value("${go-fastdfs.uploadUrl}")
    private String uploadUrl;
    @Value("${go-fastdfs.deleteUrl}")
    private String deleteUrl;

    @Override
    public ResponseBean<FileUploadResponse> upload(MultipartFile file, String userId) {
        return upload(file, userId, null);
    }

    @Override
    public ResponseBean<FileUploadResponse> upload(MultipartFile file, String userId, String serverFlag) {
        try {
            Map<String, String> uploadRespMap = getUploadRespMap(file, userId, serverFlag);
            FileUploadResponse fileUploadResponse = FileUploadResponse.builder()
                    .name(file.getOriginalFilename())
                    .url(uploadRespMap.get("src"))
                    .type(Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")))
                    .size(uploadRespMap.get("size"))
                    .md5(uploadRespMap.get("md5"))
                    .build();
            return ResponseBean.ok("上传成功", fileUploadResponse);
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    private Map<String, String> getUploadRespMap(MultipartFile file, String userId, String serverFlag) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        InputStreamResource isr = new InputStreamResource(file.getInputStream(),
                file.getOriginalFilename());
        Map<String, Object> params = new HashMap<>(4);
        params.put("file", isr);
        params.put("path", serverFlag + "/" + (StringUtils.isEmpty(userId) ? "0" : userId) + "/" + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth());
        params.put("output", "json");
        String resp = HttpUtil.post(uploadUrl, params);
        return JsonUtils.toMap(resp, String.class);
    }

    @Override
    public ResponseBean<VideoUploadResponse> uploadVideo(MultipartFile file, String userId) {
        File tempFile = null;
        try {

            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件后缀
            String prefix = fileName.substring(fileName.lastIndexOf("."));
            String tempFileName = String.valueOf(System.nanoTime());
            tempFile = File.createTempFile(tempFileName, prefix);
            file.transferTo(tempFile);
            MultimediaObject instance = new MultimediaObject(tempFile);
            MultimediaInfo info = instance.getInfo();
            long duration = info.getDuration();
            duration = duration / 1000;
            int hour = (int) (duration / 3600);
            int minute = (int) (duration % 3600) / 60;
            int second = (int) (duration - hour * 3600 - minute * 60);

            StringBuilder builder = new StringBuilder();
            if (hour > 0) {
                builder.append(hour).append("时");
            }
            if (minute > 0) {
                builder.append(minute).append("分");
            }
            if (second > 0) {
                builder.append(second).append("秒");
            }

            Map<String, String> uploadRespMap = getUploadRespMap(file, userId, null);
            VideoUploadResponse videoUploadResponse = VideoUploadResponse.builder()
                    .name(file.getOriginalFilename())
                    .url(uploadRespMap.get("src"))
                    .type(Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")))
                    .duration(builder.toString())
                    .md5(uploadRespMap.get("md5"))
                    .build();
            return ResponseBean.ok("上传成功", videoUploadResponse);
        } catch (IOException e) {
            log.error("", e);
        } catch (InputFormatException e) {
            e.printStackTrace();
        } catch (ws.schild.jave.EncoderException e) {
            e.printStackTrace();
        } finally {
            deleteFile(tempFile);
        }
        return null;
    }

    @Override
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
        } catch (Exception e) {
            log.error("", e);
        }
        responseBean.setCode(500);
        responseBean.setMsg("删除失败");
        return responseBean;
    }

    @Override
    public ResponseBean<String> uploadImg(String base64, String userId) throws IOException {
        byte[] bytes = Base64.decodeBase64(base64);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        MultipartFile file = new MockMultipartFile("file", UUID.randomUUID() + ".jpg", "multipart/form-data; charset=ISO-8859-1", in);
        ResponseBean<FileUploadResponse> fileUploadResponseResponseBean = this.upload(file, userId);
        in.close();
        String url = fileUploadResponseResponseBean.getRes().getUrl();
        ResponseBean<String> responseBean = new ResponseBean<>();
        responseBean.setRes(url);
        responseBean.setCode(ResponseStatusEnum.SUCCESS.getStatusCode());
        responseBean.setMsg("上传成功");
        return responseBean;
    }

    @Override
    public ResponseBean<FileUploadResponse> uploadImgFeign(String base64, String userId) throws IOException {
        if (base64.contains("data:")) {
            int start = base64.indexOf(",");
            base64 = base64.substring(start + 1);
        }
        byte[] bytes = Base64.decodeBase64(base64);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        MultipartFile file = new MockMultipartFile("file", UUID.randomUUID() + ".jpg", "multipart/form-data; charset=ISO-8859-1", in);
        ResponseBean<FileUploadResponse> fileUploadResponseResponseBean = this.upload(file, userId);
        in.close();
        return fileUploadResponseResponseBean;
    }

    /**
     * 删除
     *
     * @param file
     */
    private void deleteFile(File file) {
        if (Objects.nonNull(file) && file.exists()) {
            file.delete();
        }
    }
}
