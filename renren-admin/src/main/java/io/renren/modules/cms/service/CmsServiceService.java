package io.renren.modules.cms.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.modules.cms.dto.CmsServiceDTO;
import io.renren.modules.cms.dto.ServiceListDto;
import io.renren.modules.cms.entity.CmsServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 服务（产品表）
 *
 * @author cs 
 * @since 1.0 2024-06-21
 */
public interface CmsServiceService extends CrudService<CmsServiceEntity, CmsServiceDTO> {

    PageData<CmsServiceDTO> pageList(Map<String, Object> params);

    List<ServiceListDto> serviceList(Long categoryId);

}