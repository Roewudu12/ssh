package com.roe.dao.impl;

import com.roe.dao.UserDao;
import com.roe.dao.impl.BaseDaoImpl;
import com.roe.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User getByUserCode(String user_code) {

        return getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                String hql = "from User where user_code = ?1";
                Query query = session.createQuery(hql);
                query.setParameter(1,user_code);
                User user = (User) query.uniqueResult();
                return user;
            }
        });
    }
}
