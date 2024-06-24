package io.renren.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.annotation.DataFilter;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.TreeUtils;
import io.renren.modules.cms.dao.CategoriesDao;
import io.renren.modules.cms.dto.CategoriesDTO;
import io.renren.modules.cms.entity.CategoriesEntity;
import io.renren.modules.cms.service.CategoriesService;
import io.renren.modules.sys.dto.SysMenuDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class CategoriesServiceImpl extends BaseServiceImpl<CategoriesDao, CategoriesEntity> implements CategoriesService {

    /**
     *
     */
    public List<CategoriesDTO> articles(Map<String, Object> params) {
        //查询
        LambdaQueryWrapper<CategoriesEntity> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.eq(CategoriesEntity::getPid, "100");
        List<CategoriesEntity> list = baseDao.selectList(queryWrapper);
        List<CategoriesDTO> dtoList = ConvertUtils.sourceToTarget(list, CategoriesDTO.class);

        return TreeUtils.build(dtoList, 100L);
    }

    @Override
    public List<CategoriesDTO> list(Map<String, Object> params) {

        //查询
        LambdaQueryWrapper<CategoriesEntity> queryWrapper = new LambdaQueryWrapper<>();
        // queryWrapper.eq()
        List<CategoriesEntity> list = baseDao.selectList(queryWrapper);
        List<CategoriesDTO> dtoList = ConvertUtils.sourceToTarget(list, CategoriesDTO.class);

        return TreeUtils.build(dtoList, Constant.MENU_ROOT);
    }

    @Override
    public CategoriesDTO get(Long id) {
        CategoriesEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, CategoriesDTO.class);
    }

    @Override
    public void save(CategoriesDTO dto) {
        CategoriesEntity entity = ConvertUtils.sourceToTarget(dto, CategoriesEntity.class);

        insert(entity);
    }

    @Override
    public void update(CategoriesDTO dto) {
        CategoriesEntity entity = ConvertUtils.sourceToTarget(dto, CategoriesEntity.class);

        updateById(entity);
    }

    @Override
    public String getName(Long categoryId) {
        CategoriesEntity categoriesEntity = baseDao.selectById(categoryId);
        return Objects.isNull(categoriesEntity) ? null : categoriesEntity.getName();
    }

    @Override
    public List<CategoriesDTO> getList(Long id) {
        List<CategoriesEntity> entityList = baseDao.selectList(new LambdaQueryWrapper<CategoriesEntity>()
                .eq(Objects.nonNull(id), CategoriesEntity::getPid, id).orderByAsc(CategoriesEntity::getSort));
        return ConvertUtils.sourceToTarget(entityList, CategoriesDTO.class);
    }

}
