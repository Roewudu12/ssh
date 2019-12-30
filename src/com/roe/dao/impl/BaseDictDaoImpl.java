package com.roe.dao.impl;

import com.roe.dao.BaseDictDao;
import com.roe.dao.impl.BaseDaoImpl;
import com.roe.domain.BaseDict;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        //Criteria
        //创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
        //封装条件
        dc.add(Restrictions.eq("dict_type_code",dict_type_code));
        //执行
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
        return list;
    }
}
