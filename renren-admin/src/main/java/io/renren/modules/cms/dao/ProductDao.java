package io.renren.modules.cms.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.cms.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface ProductDao extends BaseDao<ProductEntity> {
	
}