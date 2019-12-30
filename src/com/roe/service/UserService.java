package com.roe.service;

import com.roe.domain.User;

public interface UserService {
    User getUserByCodePassword(User user);

    void saveUser(User user);
}
