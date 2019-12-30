package com.roe.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    //增或修改
    void saveOrUpdate(T t);
    //增
    void save(T t);
    //删
    void delete(T t);
    //删(8大基本属性都是实现了Serializable)
    void delete(Serializable id);
    //改
    void update(T t);
    //查
    T getById(Serializable id);
    //查符合条件的总记录数
    Integer getTotalCount(DetachedCriteria dc);
    //查询分页列表数据
    List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize);
}
