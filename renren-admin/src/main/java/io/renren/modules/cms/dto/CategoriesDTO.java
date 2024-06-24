package io.renren.modules.cms.dto;

import io.renren.common.utils.TreeNode;
import io.renren.modules.cms.entity.CategoriesEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分类表")
public class CategoriesDTO extends TreeNode<CategoriesDTO> implements Serializable {
    private String name;
    private Integer level;
    private String description;
    private Integer sort;
}
