package io.renren.modules.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 疑问
*
* @author cs 60@qq.com
* @since 11 2024-07-13
*/
@Data
@Schema(description = "分类表")
public class CmsInquiryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;
    private String firstName;
    private String lastName;
    @Schema(description = "公司")
    private String company;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "电话")
    private String phone;
    @Schema(description = "需求")
    private String inquiredItem;
    @Schema(description = "其他")
    private String comment;
    @Schema(description = "title")
    private String title;
    private String fromEmail;

    private String creator;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createDate;
    private String updater;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateDate;

}