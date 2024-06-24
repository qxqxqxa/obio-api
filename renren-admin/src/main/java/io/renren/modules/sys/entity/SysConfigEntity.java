package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.DateUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置
 */
@Data
@TableName("sys_config")
public class SysConfigEntity implements Serializable {

    @TableId
    private Long id;
    private String name;
    private String copyright;
    private String logo;
    private String introduce;
    private String pub1;
    private String pub2;
    private String pub3;
    private String remark;
    private String lan;
    private String del;
    private Long creator;
    private Date createDate;
    private Long updater;
    private Date updateDate;

}