/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.modules.oss.dao.SysOssDao;
import io.renren.modules.oss.entity.SysOssEntity;
import io.renren.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Service
public class SysOssServiceImpl extends BaseServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

    @Override
    public PageData<SysOssEntity> page(Map<String, Object> params) {
        QueryWrapper<SysOssEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Objects.nonNull(params.get("name")) && !String.valueOf(params.get("name")).isEmpty(), "name", params.get("name"));
        queryWrapper.like(Objects.nonNull(params.get("url")) && !String.valueOf(params.get("url")).isEmpty(), "url", params.get("url"));
        IPage<SysOssEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                queryWrapper
        );
        return getPageData(page, SysOssEntity.class);
    }
}
