package io.renren.modules.cms.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.cms.dto.CmsServiceDTO;
import io.renren.modules.cms.entity.CmsServiceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* 服务（产品表）
*
* @author cs 
* @since 1.0 2024-06-21
*/
@Mapper
public interface CmsServiceDao extends BaseDao<CmsServiceEntity> {

    List<CmsServiceDTO> pageList(@Param("param") Map<String, Object> params);

}