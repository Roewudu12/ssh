package com.roe.dao;

import com.roe.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer> {
    //按照行业统计客户数量
    List<Object[]> getIndustryCount(String industryOrSource);
}
