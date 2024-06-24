package io.renren.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.renren.common.utils.DateUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 * @since 1.0.0
 */
@Data
@Schema(description = "用户管理")
public class SysUserDTO implements Serializable {
    @Schema(description = "id")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;

    @Schema(description = "firstName", required = true)
    @NotBlank(message = "{sysuser.username.require}", groups = DefaultGroup.class)
    private String firstName;
    @Schema(description = "lastName", required = true)
    @NotBlank(message = "{sysuser.username.require}", groups = DefaultGroup.class)
    private String lastName;

    @Schema(description = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "{sysuser.password.require}", groups = AddGroup.class)
    private String password;

    @Schema(description = "邮箱")
    @Email(message = "{sysuser.email.error}", groups = DefaultGroup.class)
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "状态  0：停用    1：正常", required = true)
    @Range(min = 0, max = 1, message = "{sysuser.status.range}", groups = DefaultGroup.class)
    private Integer status;

    @Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createDate;

    @Schema(description = "超级管理员   0：否   1：是")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer superAdmin;

    @Schema(description = "角色ID列表")
    private List<Long> roleIdList;

    public String getUsername() {
        return this.getEmail();
    }
}