package io.renren.modules.cms.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.cms.dto.ArticlesDTO;
import io.renren.modules.cms.dto.ArticlesDTO;
import io.renren.modules.cms.entity.ArticlesEntity;
import io.renren.modules.cms.service.ArticlesService;
import io.renren.modules.cms.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 新闻
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("cms/articles")
@Tag(name = "文章管理")
public class ArticlesController {
    private final ArticlesService newsService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "title", description = "标题"),
            @Parameter(name = "startDate", description = "开始时间"),
            @Parameter(name = "endDate", description = "结束时间"),
    })
    public Result<PageData<ArticlesDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<ArticlesDTO> page = newsService.page(params);

        return new Result<PageData<ArticlesDTO>>().ok(page);
    }

    @Operation(summary = "信息")
    @GetMapping("{id}")
    public Result<ArticlesDTO> info(@PathVariable("id") Long id) {
        ArticlesDTO news = newsService.get(id);

        return new Result<ArticlesDTO>().ok(news);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    public Result save(ArticlesDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        newsService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    public Result update(ArticlesDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        newsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        for (Long id : ids) {
            ArticlesEntity entity = new ArticlesEntity();
            entity.setId(id);
            entity.setDel(1);
            newsService.updateById(entity);
        }

        return new Result();
    }

}