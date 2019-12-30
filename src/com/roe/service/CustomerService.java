package com.roe.service;

import com.roe.domain.Customer;
import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {
    //分页业务方法
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    //保存客户
    void save(Customer customer);

    //根据Id获得客户对象
    Customer getById(Long cust_id);

    //按行统计客户数量
    List<Object[]> getIndustryCount(String industryOrSource);

    //删除客户
    void delete(Customer customer);
}
