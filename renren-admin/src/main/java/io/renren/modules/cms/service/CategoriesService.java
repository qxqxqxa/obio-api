package io.renren.modules.cms.service;

import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.cms.dto.CategoriesDTO;
import io.renren.modules.cms.entity.CategoriesEntity;

import java.util.List;
import java.util.Map;


public interface CategoriesService extends BaseService<CategoriesEntity> {

    List<CategoriesDTO> articles(Map<String, Object> params);

    List<CategoriesDTO> list(Map<String, Object> params);

    CategoriesDTO get(Long id);

    void save(CategoriesDTO dto);

    void update(CategoriesDTO dto);

    String getName(Long categoryId);

    List<CategoriesDTO> getList(Long id);
}

