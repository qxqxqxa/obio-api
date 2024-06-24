package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {


}