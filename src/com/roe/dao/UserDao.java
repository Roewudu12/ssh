package com.roe.dao;

import com.roe.domain.User;

public interface UserDao extends BaseDao<User> {
    User getByUserCode(String user_code);
}
