package com.roe.service.impl;


import com.roe.dao.SaleVisitDao;
import com.roe.domain.Customer;
import com.roe.domain.SaleVisit;
import com.roe.service.SaleVisitService;
import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class SaleVisitServiceImpl implements SaleVisitService {
    private SaleVisitDao svd;
    @Override
    public void save(SaleVisit saleVisit) {
        if(saleVisit.getVisit_id().equals("")){
            svd.save(saleVisit);
        }else {
            svd.update(saleVisit);
        }
    }

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //调用DAO查询总记录数(离线查询对象)
        Integer totalCount = svd.getTotalCount(dc);
        //创建PageBean对象
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);
        //调用DAO查询分页列表数据
        List<SaleVisit> list = svd.getPageList(dc,pb.getStart(),pb.getPageSize());
        //将列表数据放入PageBean中并返回
        pb.setList(list);
        return pb;
    }

    @Override
    public SaleVisit getById(String visit_id) {
        return svd.getById(visit_id);
    }

    @Override
    public void delete(SaleVisit saleVisit) {
        svd.delete(saleVisit);
    }

    public void setSvd(SaleVisitDao svd) {
        this.svd = svd;
    }
}

