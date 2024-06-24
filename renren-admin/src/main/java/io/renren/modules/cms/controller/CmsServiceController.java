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
import io.renren.modules.cms.dto.CmsServiceDTO;
import io.renren.modules.cms.dto.ServiceListDto;
import io.renren.modules.cms.service.CmsServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
* 服务（产品表）
*
* @author cs 
* @since 1.0 2024-06-21
*/
@RestController
@RequestMapping("cms/service")
@Tag(name ="服务产品")
public class CmsServiceController {
    @Autowired
    private CmsServiceService cmsServiceService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
    })
    public Result<PageData<CmsServiceDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<CmsServiceDTO> page = cmsServiceService.pageList(params);

        return new Result<PageData<CmsServiceDTO>>().ok(page);
    }

    @GetMapping("/public/list")
    @Operation(summary = "列表")
    @Parameter(name ="categoryId", description = "类别ID")
    public Result<List<ServiceListDto>> serviceList(Long categoryId){
        if (Objects.isNull(categoryId)) {
            //ID没传的情况下 服务模块 ID暂时写死
            categoryId = 1804037380862840833l;
        }
        List<ServiceListDto> data = cmsServiceService.serviceList(categoryId);

        return new Result<List<ServiceListDto>>().ok(data);
    }

    @GetMapping("/public/info")
    @Operation(summary = "详情")
    @Parameter(name ="id", description = "服务ID")
    public Result<CmsServiceDTO> info(Long id){
        if (Objects.isNull(id)) {
            return new Result<CmsServiceDTO>().error("ID不能为空");
        }
        CmsServiceDTO data = cmsServiceService.get(id);

        return new Result<CmsServiceDTO>().ok(data);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<CmsServiceDTO> get(@PathVariable("id") Long id){
        CmsServiceDTO data = cmsServiceService.get(id);

        return new Result<CmsServiceDTO>().ok(data);
    }

    @PostMapping
    @LogOperation("保存")
    @Operation(summary = "保存")
    public Result save(@RequestBody CmsServiceDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        cmsServiceService.save(dto);

        return new Result();
    }

    @PutMapping
    @LogOperation("修改")
    @Operation(summary = "修改")
    public Result update(@RequestBody CmsServiceDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        cmsServiceService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "修改")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        cmsServiceService.delete(ids);

        return new Result();
    }

//    @GetMapping("export")
//    @Operation(summary = "导出")
//    @LogOperation("导出")
//    @RequiresPermissions("cms:cmsservice:export")
//    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        List<CmsServiceDTO> list = cmsServiceService.list(params);
//
//        ExcelUtils.exportExcelToTarget(response, null, "服务（产品表）", list, CmsServiceExcel.class);
//    }

}