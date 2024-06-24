package io.renren.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.cms.dao.ProductDao;
import io.renren.modules.cms.dto.ProductDTO;
import io.renren.modules.cms.dto.ProductParamsDTO;
import io.renren.modules.cms.entity.ProductEntity;
import io.renren.modules.cms.service.ProductParamsService;
import io.renren.modules.cms.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 产品管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@Service
public class ProductServiceImpl extends CrudServiceImpl<ProductDao, ProductEntity, ProductDTO> implements ProductService {
    private final ProductParamsService productParamsService;

    @Override
    public QueryWrapper<ProductEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        return wrapper;
    }

    @Override
    public ProductDTO get(Long id) {
        ProductDTO dto = super.get(id);

        // 获取子表数据
        List<ProductParamsDTO> subList = productParamsService.getList(id);
        dto.setSubList(subList);

        return dto;
    }

    @Override
    @Transactional
    public void save(ProductDTO dto) {
        super.save(dto);

        // 保存子表数据
        productParamsService.saveOrUpdate(dto.getId(), dto.getSubList());
    }

    @Override
    @Transactional
    public void update(ProductDTO dto) {
        super.update(dto);

        // 更新子表数据
        productParamsService.saveOrUpdate(dto.getId(), dto.getSubList());
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        super.delete(ids);

        // 删除子表数据
        productParamsService.deleteByProductIds(ids);
    }
}