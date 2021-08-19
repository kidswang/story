package com.waiwaiwai.story.file.biz;

import com.waiwaiwai.story.response.FileUploadResponse;
import com.waiwaiwai.story.response.ResponseBean;
import com.waiwaiwai.story.response.VideoUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.IOException;

public interface FileBiz {

    /**
     * upload
     * @param file MultipartFile
     * @param userId String
     * @return ResponseBean
     */
    ResponseBean<FileUploadResponse> upload(MultipartFile file, String userId);

    /**
     * upload
     * @param file MultipartFile
     * @param userId String
     * @param serverFlag String
     * @return ResponseBean
     */
    ResponseBean<FileUploadResponse> upload(MultipartFile file, String userId, String serverFlag);

    /**
     * uploadVideo
     * @param file MultipartFile
     * @param userId String
     * @return ResponseBean
     */
    ResponseBean<VideoUploadResponse> uploadVideo(MultipartFile file, String userId);

    /**
     * deleteFile
     * @param md5 String
     * @return ResponseBean
     */
    ResponseBean<Null> deleteFile(String md5);

    /**
     *
     * uploadImg
     * @param base64 String
     * @param userId String
     * @return ResponseBean
     * @throws IOException e
     */
    ResponseBean<String> uploadImg(String base64, String userId) throws IOException;

    /**
     * uploadImgFeign
     * @param base64 String
     * @param userId String
     * @return ResponseBean
     * @throws IOException e
     */
    ResponseBean<FileUploadResponse> uploadImgFeign(String base64, String userId) throws IOException;
}
