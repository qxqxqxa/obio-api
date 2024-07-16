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
import io.renren.modules.cms.dto.CmsInquiryDTO;
import io.renren.modules.cms.service.CmsInquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
* 疑问
*
* @author cs 60@qq.com
* @since 11 2024-07-13
*/
@AllArgsConstructor
@RestController
@RequestMapping("cms/inquiry")
@Tag(name = "疑问")
public class CmsInquiryController {
    private final CmsInquiryService cmsInquiryService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)")
    })
    public Result<PageData<CmsInquiryDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<CmsInquiryDTO> page = cmsInquiryService.page(params);

        return new Result<PageData<CmsInquiryDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<CmsInquiryDTO> get(@PathVariable("id") Long id){
        CmsInquiryDTO data = cmsInquiryService.get(id);

        return new Result<CmsInquiryDTO>().ok(data);
    }

    @PostMapping("save")
    @Operation(summary = "保存")
    @LogOperation("保存")
    public Result save(@RequestBody CmsInquiryDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        cmsInquiryService.add(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    public Result update(@RequestBody CmsInquiryDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        cmsInquiryService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        cmsInquiryService.delete(ids);

        return new Result();
    }


}