package com.waiwaiwai.story.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mhwangzl
 * @date 2021/8/18 10:51
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileUploadResponse implements Serializable {

    private static final long serialVersionUID = 3405430749243802382L;

    private String name;

    private String url;

    private String type;

    private String size;

    private String md5;
}
