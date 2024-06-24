package io.renren.modules.cms.service;

import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.cms.dto.ArticlesDTO;
import io.renren.modules.cms.dto.NewsDTO;
import io.renren.modules.cms.entity.ArticlesEntity;
import io.renren.modules.cms.entity.CategoriesEntity;
import io.renren.modules.cms.vo.NewsVo;

import java.util.List;
import java.util.Map;


public interface ArticlesService extends BaseService<ArticlesEntity> {

    PageData<ArticlesDTO> page(Map<String, Object> params);
    ArticlesDTO get(Long id);

    void save(ArticlesDTO dto);

    void update(ArticlesDTO dto);
}

