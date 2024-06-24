/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(description = "注册表单")
public class RegisterDTO {
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

}
