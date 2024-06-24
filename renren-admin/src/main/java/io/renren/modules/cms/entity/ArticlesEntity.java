package io.renren.modules.cms.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_articles")
public class ArticlesEntity extends BaseEntity {

    private String title;
    private Long categoryId;
    private String summary;
    private String keywords;
    private String content;
    private String source;
    private String sourceUrl;
    /**
     * 头图
     */
    private String subPic;
    private String author;
    private Long promotionDic;
    private Long status;
    private Integer del;
    private Integer views;
    private Integer commentsCount;
    private Date pubDate;
    private Date eventTime;
    private String location;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updater;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
}