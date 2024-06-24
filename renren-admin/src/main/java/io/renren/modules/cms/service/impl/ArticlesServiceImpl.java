package io.renren.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.modules.cms.dao.ArticlesDao;
import io.renren.modules.cms.dao.CategoriesDao;
import io.renren.modules.cms.dto.ArticlesDTO;
import io.renren.modules.cms.entity.ArticlesEntity;
import io.renren.modules.cms.entity.CategoriesEntity;
import io.renren.modules.cms.service.ArticlesService;
import io.renren.modules.cms.service.CategoriesService;
import io.renren.modules.cms.vo.NewsVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@AllArgsConstructor
public class ArticlesServiceImpl extends BaseServiceImpl<ArticlesDao, ArticlesEntity> implements ArticlesService {

    final CategoriesService categoriesService;
    final CategoriesDao categoriesDao;

    /**
     * mybatis数据权限演示
     */
    @Override
    public PageData<ArticlesDTO> page(Map<String, Object> params) {
        //分页
        IPage<ArticlesEntity> page = getPage(params, null, false);
        page.orders().add(OrderItem.desc("event_time"));
        page.orders().add(OrderItem.desc("pub_date"));

        //查询
        LambdaQueryWrapper<ArticlesEntity> queryWrapper = new LambdaQueryWrapper<>();

        CategoriesEntity categoriesEntity = categoriesDao.selectOne(new LambdaQueryWrapper<CategoriesEntity>().eq(CategoriesEntity::getPid, 100).eq(CategoriesEntity::getName, params.get("type")));
        if (Objects.nonNull(categoriesEntity)) {
            queryWrapper.eq(ArticlesEntity::getCategoryId, categoriesEntity.getId());
        }
        Object obj = params.get("categoryId");
        queryWrapper.eq(ArticlesEntity::getDel, 0);
        queryWrapper.eq(Objects.nonNull(obj) && !"0".equals(String.valueOf(obj)), ArticlesEntity::getCategoryId, obj);
        queryWrapper.eq(Objects.nonNull(params.get("status")) && !"0".equals(String.valueOf(params.get("status"))), ArticlesEntity::getStatus, params.get("status"));
        queryWrapper.like(Objects.nonNull(params.get("title")) && !String.valueOf(params.get("title")).isEmpty(), ArticlesEntity::getTitle, params.get("title"));

        if (Objects.equals("1", obj)) {
            queryWrapper.ge(Objects.nonNull(params.get("startDate")), ArticlesEntity::getPubDate, params.get("startDate"));
            queryWrapper.le(Objects.nonNull(params.get("endDate")), ArticlesEntity::getPubDate, params.get("endDate"));
        } else if (Objects.equals("2", obj)) {
            queryWrapper.ge(Objects.nonNull(params.get("startDate")), ArticlesEntity::getEventTime, params.get("startDate"));
            queryWrapper.le(Objects.nonNull(params.get("endDate")), ArticlesEntity::getEventTime, params.get("endDate"));
        } else {
            queryWrapper.ge(Objects.nonNull(params.get("startDate")), ArticlesEntity::getPubDate, params.get("startDate"));
            queryWrapper.le(Objects.nonNull(params.get("endDate")), ArticlesEntity::getPubDate, params.get("endDate"));
        }
        List<ArticlesEntity> list = baseDao.selectList(page, queryWrapper);

        PageData<ArticlesDTO> pageData = getPageData(list, page.getTotal(), ArticlesDTO.class);
        pageData.getList().forEach(e -> e.setCategoryName(categoriesService.getName(e.getCategoryId())));
        return pageData;
    }

    @Override
    public ArticlesDTO get(Long id) {
        ArticlesEntity entity = baseDao.selectById(id);

        ArticlesDTO articlesDTO = ConvertUtils.sourceToTarget(entity, ArticlesDTO.class);
        articlesDTO.setCategoryName(categoriesService.getName(articlesDTO.getCategoryId()));
        return articlesDTO;
    }

    @Override
    public void save(ArticlesDTO dto) {
        ArticlesEntity entity = ConvertUtils.sourceToTarget(dto, ArticlesEntity.class);
        entity.setDel(0);
        insert(entity);
    }

    @Override
    public void update(ArticlesDTO dto) {
        ArticlesEntity entity = ConvertUtils.sourceToTarget(dto, ArticlesEntity.class);

        updateById(entity);
    }

}
