package com.roe.service;

import com.roe.domain.LinkMan;
import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {
    //保存联系人
    void save(LinkMan linkMan);
    //分页业务
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
    //根据ID获得LinkMan对象
    LinkMan getById(Long lkm_id);
    //删除联系人
    void delete(LinkMan linkMan);
}
