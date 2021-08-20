package com.waiwaiwai.story.bo;

import lombok.Data;

import java.util.List;

@Data
public class ContentInfoReturn {

    private Long totalCount;

    private List<ContentInfoBo> contentInfoBos;

}
