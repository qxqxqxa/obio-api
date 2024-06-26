package io.renren.modules.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.DateUtils;
import io.renren.modules.cms.entity.ArticlesEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(description = "文章")
public class ArticlesDTO implements Serializable {
  @Schema(description = "数据id") private Long id;
  @Schema(description = "小标题") private String title;
  @Schema(description = "分类ID，关联分类表") private Long categoryId;
  @Schema(description = "分类名称") private String categoryName;
  @Schema(description = "摘要或简介") private String summary;
  @Schema(description = "关键词") private String keywords;
  @Schema(description = "内容") private String content;
  @Schema(description = "来源") private String source;
  @Schema(description = "来源url") private String sourceUrl;
  @Schema(description = "头图") private String subPic;
  @Schema(description = "作者") private String author;
  @Schema(description = "推广 0：正常 10：置顶  9：热点") private Long promotionDic;
  @Schema(description = "状态，关联字典表。草稿，已发布") private Long status;
  @Schema(description = "0：正常 1：删除") private Integer del;
  @Schema(description = "浏览次数") private Integer views;
  @Schema(description = "评论数量") private Integer commentsCount;
   @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
  @Schema(description = "发布日期") private Date pubDate;
    @Schema(description = "创建人")
    private String creator;
  @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
  @Schema(description = "事件时间")
  private Date eventTime;
  @Schema(description = "位置")
  private String location;

}
