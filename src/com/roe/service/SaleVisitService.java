package com.roe.service;

import com.roe.domain.SaleVisit;
import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface SaleVisitService {
    //保存客户拜访记录
    void save(SaleVisit saleVisit);
    //客户拜访记录列表
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
    //通过id获取客户拜访记录
    SaleVisit getById(String visit_id);
    //删除拜访记录
    void delete(SaleVisit saleVisit);
}
