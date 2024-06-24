package io.renren.modules.cms.controller;

import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.modules.cms.dto.ArticlesDTO;
import io.renren.modules.cms.service.ArticlesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 新闻
 */
@AllArgsConstructor
@RestController
@RequestMapping("/cms/public/")
@Tag(name = "新闻管理")
public class PublicController {
    private final ArticlesService articlesService;

    @GetMapping("/articles/{type}/page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "title", description = "标题"),
    })
    public Result<PageData<ArticlesDTO>> page(@PathVariable("type") String type, @Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        params.put("type", type);
        PageData<ArticlesDTO> page = articlesService.page(params);
        return new Result<PageData<ArticlesDTO>>().ok(page);
    }

    @GetMapping("/articles/{id}")
    @Operation(summary = "详情")
    public Result<ArticlesDTO> info(@PathVariable("id") Long id) {
        ArticlesDTO page = articlesService.get(id);
        return new Result<ArticlesDTO>().ok(page);
    }
}