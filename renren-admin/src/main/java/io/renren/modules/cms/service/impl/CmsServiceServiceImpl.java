package io.renren.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.common.user.UserDetail;
import io.renren.modules.cms.dao.CategoriesDao;
import io.renren.modules.cms.dao.CmsServiceDao;
import io.renren.modules.cms.dto.CmsServiceDTO;
import io.renren.modules.cms.dto.ServiceListDto;
import io.renren.modules.cms.entity.CategoriesEntity;
import io.renren.modules.cms.entity.CmsServiceEntity;
import io.renren.modules.cms.service.CmsServiceService;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.sys.dto.SysUserDTO;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.enums.SuperAdminEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 服务（产品表）
 *
 * @author cs 
 * @since 1.0 2024-06-21
 */
@Service
public class CmsServiceServiceImpl extends CrudServiceImpl<CmsServiceDao, CmsServiceEntity, CmsServiceDTO> implements CmsServiceService {

    @Autowired
    private CategoriesDao categoriesDao;
    
    @Override
    public QueryWrapper<CmsServiceEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<CmsServiceEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


    @Override
    public PageData<CmsServiceDTO> pageList(Map<String, Object> params) {
        //分页
        IPage<CmsServiceEntity> page = getPage(params, null, false);

        //查询
        List<CmsServiceDTO> list = baseDao.pageList(params);

        return getPageData(list, page.getTotal(), CmsServiceDTO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public List<ServiceListDto> serviceList(Long categoryId) {
        List<CategoriesEntity> categoriesEntities = categoriesDao.selectList(new LambdaQueryWrapper<CategoriesEntity>()
                .eq(CategoriesEntity::getPid, categoryId).orderByAsc(CategoriesEntity::getSort));

        List<ServiceListDto> list = new ArrayList<>();
        for (CategoriesEntity categories : categoriesEntities) {
            //类别
            list.add(ServiceListDto.builder().type(0).categoryId(categories.getId())
                    .id(categories.getId()).title(categories.getName()).build());
            //服务
            List<CmsServiceEntity> services = this.baseDao.selectList(new LambdaQueryWrapper<CmsServiceEntity>()
                    .eq(CmsServiceEntity::getCategoryId, categories.getId()).orderByAsc(CmsServiceEntity::getSort));
            for (CmsServiceEntity service : services) {
                list.add(ServiceListDto.builder().type(1).categoryId(categories.getId())
                        .id(service.getId()).title(service.getTitle()).build());
            }

        }

        return list;
    }
}