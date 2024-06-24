package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.utils.Result;
import io.renren.modules.sys.dao.SysConfigDao;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys/config")
@Tag(name = "部门管理")
public class SysConfigController {
    private final SysConfigDao sysConfigDao;

    @GetMapping("/info")
    @Operation(summary = "信息")
    public Result<SysConfigEntity> get() {
        List<SysConfigEntity> sysConfigEntities = sysConfigDao.selectList(null);
        if (sysConfigEntities.isEmpty()) {
            sysConfigEntities.add(new SysConfigEntity());
        }

        return new Result<SysConfigEntity>().ok(sysConfigEntities.get(0));
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    public Result update(@RequestBody SysConfigEntity dto) {

        List<SysConfigEntity> sysConfigEntities = sysConfigDao.selectList(null);
        if (sysConfigEntities.isEmpty()) {
            sysConfigDao.insert(dto);
        } else {
            SysConfigEntity sysConfigEntity = sysConfigEntities.get(0);
            BeanUtils.copyProperties(dto, sysConfigEntity);
            sysConfigDao.updateById(sysConfigEntity);
        }

        return new Result();
    }
}