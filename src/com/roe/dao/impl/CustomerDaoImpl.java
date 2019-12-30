package com.roe.dao.impl;

import com.roe.dao.CustomerDao;

import com.roe.domain.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.query.NativeQuery;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.List;


public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    @Override
    @SuppressWarnings("all")//忽略警告
    public List<Object[]> getIndustryCount(String industryOrSource) {
        //使用原生Sql查询
       return getHibernateTemplate().execute(new HibernateCallback<List>() {
           String sql = "SELECT bd.dict_item_name,COUNT(*) total FROM cst_customer c,base_dict bd WHERE c.cust_"+industryOrSource+" = bd.dict_id GROUP BY c.cust_"+industryOrSource+" ,bd.dict_item_name";
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                NativeQuery query = session.createSQLQuery(sql);
                return query.list();
            }
        });
    }
}
