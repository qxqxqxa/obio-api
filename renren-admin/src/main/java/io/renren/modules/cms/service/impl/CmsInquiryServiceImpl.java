package io.renren.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.RenException;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.common.utils.JsonUtils;
import io.renren.modules.cms.dao.CmsInquiryDao;
import io.renren.modules.cms.dto.CmsInquiryDTO;
import io.renren.modules.cms.entity.CmsInquiryEntity;
import io.renren.modules.cms.service.CmsInquiryService;
import io.renren.modules.message.dao.SysMailTemplateDao;
import io.renren.modules.message.email.EmailUtils;
import io.renren.modules.message.entity.SysMailTemplateEntity;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.sys.service.SysParamsService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 疑问
 *
 * @author cs 60@qq.com
 * @since 11 2024-07-13
 */
@Service
public class CmsInquiryServiceImpl extends CrudServiceImpl<CmsInquiryDao, CmsInquiryEntity, CmsInquiryDTO> implements CmsInquiryService {
    @Resource
    private  EmailUtils emailUtils;
    @Resource
    private SysMailTemplateDao sysMailTemplateDao;
    @Autowired
    private SysParamsService paramsService;
    @Override
    public QueryWrapper<CmsInquiryEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<CmsInquiryEntity> wrapper = new QueryWrapper<>();
        wrapper.like(params.containsKey("company"),"company",params.get("company"));
        wrapper.like(params.containsKey("email"),"email",params.get("email"));
        wrapper.like(params.containsKey("phone"),"phone",params.get("phone"));
        wrapper.like(params.containsKey("title"),"title",params.get("title"));
        wrapper.orderByDesc("create_date");
        return wrapper;
    }


    @Override
    public void add(CmsInquiryDTO dto) {
        save(dto);
        SysMailTemplateEntity message = sysMailTemplateDao.selectOne(new LambdaQueryWrapper<SysMailTemplateEntity>().like(SysMailTemplateEntity::getSubject, "message").last("limit 1"));
        Map<String, Object> map = new HashMap<>();
        //获取客服邮箱
        String mailTo = paramsService.getValue(Constant.TO_EMAIL);
        if (StringUtils.isBlank(mailTo)) {
            throw new RenException("The customer service email has not been configured！");
        }
        String[] to = new String[]{mailTo};
        String[] cc =null;
        String user = StringUtils.isNotBlank(dto.getFirstName()) ? dto.getFirstName() + " "+ dto.getLastName():"";
        map.put("user",user);
        map.put("from_name",user);
        map.put("comment",dto.getComment());
        map.put("interest",dto.getInquiredItem());
        map.put("title",dto.getTitle());
        map.put("company",dto.getCompany());
        map.put("phone",dto.getPhone());
        map.put("email",dto.getEmail());
        try {
             emailUtils.sendMail( message.getId(), to, cc, map);
        } catch (Exception e) {
            throw new RenException("Send email failed：【{}】",e.getCause());
        }
    }
}