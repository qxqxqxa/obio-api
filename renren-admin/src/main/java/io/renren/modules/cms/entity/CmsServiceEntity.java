package io.renren.modules.cms.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务（产品表）
 *
 * @author cs 
 * @since 1.0 2024-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("cms_service")
public class CmsServiceEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	* 标题
	*/
	private String title;
	/**
	* 分类ID，关联分类表
	*/
	private Long categoryId;
	/**
	* 摘要或简介
	*/
	private String summary;
	/**
	* 关键词，多个已英文逗号,分隔
	*/
	private String keywords;
	/**
	* 缩略图，如果多张用英文,分割
	*/
	private String subPic;
	/**
	* 内容
	*/
	private String content;
	/**
	* 推广 0：正常 10：置顶  9：热点
	*/
	private Long promotionDic;
	/**
	* 状态，关联字典表。草稿，已发布
	*/
	private Long status;
	/**
	* 价格（预留）
	*/
	private BigDecimal price;
	/**
	* 最低价格（预留）
	*/
	private BigDecimal priceMin;
	/**
	* 0：正常 1：删除
	*/
	private Integer del;
	/**
	* 发布日期
	*/
	private Date pubDate;
	/**
	* 更新者
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	* 更新时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;

	private Integer sort;
}