package com.roe.dao;

import com.roe.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao {
    Integer getTotalCount(DetachedCriteria dc);

    List<Customer> getPageList(DetachedCriteria dc, int start, Integer pageSize);
}
