package io.renren.modules.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 服务（产品表）
*
* @author cs 
* @since 1.0 2024-06-21
*/
@Data
@Schema(description = "服务（产品表）")
public class CmsServiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "分类ID，关联分类表")
    private Long categoryId;
    @Schema(description = "摘要或简介")
    private String summary;
    @Schema(description = "关键词，多个已英文逗号,分隔")
    private String keywords;
    @Schema(description = "缩略图，如果多张用英文,分割")
    private String subPic;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "推广 0：正常 10：置顶  9：热点")
    private Long promotionDic;
    @Schema(description = "状态，关联字典表。草稿，已发布")
    private Long status;
    @Schema(description = "价格（预留）")
    private BigDecimal price;
    @Schema(description = "最低价格（预留）")
    private BigDecimal priceMin;
    @Schema(description = "0：正常 1：删除")
    private Integer del;
    @Schema(description = "发布日期")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date pubDate;
    @Schema(description = "创建者")
    private Long creator;
    @Schema(description = "创建时间")
    private Date createDate;
    @Schema(description = "更新者")
    private Long updater;
    @Schema(description = "更新时间")
    private Date updateDate;
    @Schema(description = "分类")
    private String categoryName;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "操作账号")
    private String username;

}