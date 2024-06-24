package io.renren.modules.cms.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.cms.entity.ExcelDataEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Excel导入演示
*
* @author Mark sunlightcs@gmail.com
*/
@Mapper
public interface ExcelDataDao extends BaseDao<ExcelDataEntity> {
	
}