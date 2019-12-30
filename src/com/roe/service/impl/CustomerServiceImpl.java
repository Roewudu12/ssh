package com.roe.service.impl;

import com.opensymphony.xwork2.ActionContext;
import com.roe.dao.CustomerDao;
import com.roe.domain.Customer;
import com.roe.service.CustomerService;
import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;
import java.util.Map;

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

    @Override
    public void save(Customer customer) {
        //维护customer与数据字典对象的关系，由于struts2参数封装，会将参数封装到数据字典的id属性
        //那么我们无需手动维护关系
        //调用dao保存客户
        cd.saveOrUpdate(customer);
    }

    @Override
    public Customer getById(Long cust_id) {
        return cd.getById(cust_id);
    }

    @Override
    public List<Object[]> getIndustryCount(String industryOrSource) {

        return cd.getIndustryCount(industryOrSource);
    }

    @Override
    public void delete(Customer customer) {
        cd.delete(customer);
    }

    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }
}
