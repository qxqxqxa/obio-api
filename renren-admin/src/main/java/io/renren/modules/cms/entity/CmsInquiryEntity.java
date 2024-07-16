package io.renren.modules.cms.entity;

import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

/**
 * 疑问
 *
 * @author cs 60@qq.com
 * @since 11 2024-07-13
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("cms_inquiry")
public class CmsInquiryEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	@TableId
	private Long id;
	private String firstName;
	private String lastName;
	/**
	* 公司
	*/
	private String company;
	/**
	* 邮箱
	*/
	private String email;
	/**
	* 电话
	*/
	private String phone;
	/**
	* 需求
	*/
	private String inquiredItem;
	private String title;
	/**
	* 其他
	*/
	private String comment;
	private String fromEmail;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
}