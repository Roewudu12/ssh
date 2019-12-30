package com.roe.service.impl;

import com.roe.dao.LinkManDao;
import com.roe.domain.Customer;
import com.roe.domain.LinkMan;
import com.roe.service.LinkManService;
import com.roe.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //调用DAO查询总记录数(离线查询对象)
        Integer totalCount = linkManDao.getTotalCount(dc);
        //创建PageBean对象
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);
        //调用DAO查询分页列表数据
        List<LinkMan> list = linkManDao.getPageList(dc,pb.getStart(),pb.getPageSize());
        //将列表数据放入PageBean中并返回
        pb.setList(list);
        return pb;
    }

    @Override
    public LinkMan getById(Long lkm_id) {

        return linkManDao.getById(lkm_id);
    }

    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.saveOrUpdate(linkMan);
    }

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }
}
