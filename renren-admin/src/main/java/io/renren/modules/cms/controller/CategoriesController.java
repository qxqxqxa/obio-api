package io.renren.modules.cms.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.cms.dto.ArticlesDTO;
import io.renren.modules.cms.dto.CategoriesDTO;
import io.renren.modules.cms.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 新闻
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/cms/categories")
@Tag(name = "类别管理")
public class CategoriesController {
    private final CategoriesService categoriesService;

    @GetMapping("articles")
    @Operation(summary = "分页")
    public Result<List<CategoriesDTO>> articles() {
        List<CategoriesDTO> page = categoriesService.articles(new HashMap<>());
        return new Result<List<CategoriesDTO>>().ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
    })
    public Result<List<CategoriesDTO>> list(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        List<CategoriesDTO> page = categoriesService.list(params);
        if (Objects.equals("0", String.valueOf(params.get("type")))) {
            CategoriesDTO element = new CategoriesDTO();
            element.setId(0L);
            element.setName("All");
            page.add(0, element);
        }
        return new Result<List<CategoriesDTO>>().ok(page);
    }

    @Operation(summary = "信息")
    @GetMapping("{id}")
    public Result<CategoriesDTO> info(@PathVariable("id") Long id) {
        CategoriesDTO news = categoriesService.get(id);

        return new Result<CategoriesDTO>().ok(news);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    public Result save(@RequestBody CategoriesDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        categoriesService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    public Result update(@RequestBody CategoriesDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        categoriesService.update(dto);

        return new Result();
    }

    @Operation(summary = "信息")
    @DeleteMapping("{id}")
    public Result<CategoriesDTO> delete(@PathVariable("id") Long id) {
        categoriesService.deleteBatchIds(Collections.singletonList(id));
        return new Result<CategoriesDTO>().ok(null);
    }

    @GetMapping("/all/list")
    @Operation(summary = "分类列表")
    @Parameter(name = "pid", description = "上级ID", required = false)
    public Result<List<CategoriesDTO>> list(Long pid) {
        List<CategoriesDTO> list = categoriesService.getList(pid);
        return new Result<List<CategoriesDTO>>().ok(list);
    }
}