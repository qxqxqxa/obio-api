package io.renren.modules.cms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cs
 * @date 2024/6/21 17:21
 * @description:
 */
@Setter
@Getter
@Builder
public class ServiceListDto {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "摘要或简介")
    private String summary;
    @Schema(description = "分类ID")
    private Long categoryId;
    @Schema(description = "类型 0-类别 1-服务")
    private int type;

}
