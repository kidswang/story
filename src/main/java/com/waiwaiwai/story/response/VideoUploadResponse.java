package com.waiwaiwai.story.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mhwangzl
 * @date 2021/8/18 11:27
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoUploadResponse implements Serializable {
    private static final long serialVersionUID = -8088652483019518682L;
    private String name;

    private String url;

    private String type;

    private String duration;

    private String md5;
}
