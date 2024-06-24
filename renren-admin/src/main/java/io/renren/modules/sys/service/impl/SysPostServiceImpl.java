package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.modules.sys.dao.SysPostDao;
import io.renren.modules.sys.dto.SysPostDTO;
import io.renren.modules.sys.entity.SysPostEntity;
import io.renren.modules.sys.service.SysPostService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 岗位管理
 */
@AllArgsConstructor
@Service
public class SysPostServiceImpl extends CrudServiceImpl<SysPostDao, SysPostEntity, SysPostDTO> implements SysPostService {

    @Override
    public QueryWrapper<SysPostEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<SysPostEntity> wrapper = new QueryWrapper<>();

        String postCode = (String) params.get("postCode");
        wrapper.like(StringUtils.isNotBlank(postCode), "post_code", postCode);

        String postName = (String) params.get("postName");
        wrapper.like(StringUtils.isNotBlank(postName), "post_name", postName);

        String status = (String) params.get("status");
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq("status", Integer.parseInt(status));
        }

        wrapper.orderByAsc("sort");

        return wrapper;
    }

    @Override
    public List<SysPostDTO> list(Map<String, Object> params) {
        List<SysPostEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysPostDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除岗位
        baseDao.deleteBatchIds(Arrays.asList(ids));

    }
}