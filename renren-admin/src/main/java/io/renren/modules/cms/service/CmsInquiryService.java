package io.renren.modules.cms.service;

import io.renren.common.service.CrudService;
import io.renren.modules.cms.dto.CmsInquiryDTO;
import io.renren.modules.cms.entity.CmsInquiryEntity;

/**
 * 疑问
 *
 * @author cs 60@qq.com
 * @since 11 2024-07-13
 */
public interface CmsInquiryService extends CrudService<CmsInquiryEntity, CmsInquiryDTO> {

    void add(CmsInquiryDTO dto);
}