package io.renren.modules.cms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class NewsVo {
    private Long id;
    private String type;
    private String title;
    @Schema(description = "time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date time;
    private String img;
    private String subhead;
    private String content;
    private String html;
}
