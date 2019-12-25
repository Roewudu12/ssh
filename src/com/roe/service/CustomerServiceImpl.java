package com.roe.service;

import com.roe.dao.CustomerDao;
import com.roe.domain.Customer;
import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao cd;
    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //调用DAO查询总记录数(离线查询对象)
        Integer totalCount = cd.getTotalCount(dc);
        //创建PageBean对象
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);
        //调用DAO查询分页列表数据
        List<Customer> list = cd.getPageList(dc,pb.getStart(),pb.getPageSize());
        //将列表数据放入PageBean中并返回
        pb.setList(list);
        return pb;
    }

    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }
}
