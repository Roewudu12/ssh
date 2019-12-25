package com.roe.service;

import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface CustomerService {
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
}
