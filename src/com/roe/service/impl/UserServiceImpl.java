package com.roe.service.impl;

import com.roe.dao.UserDao;
import com.roe.domain.User;
import com.roe.service.UserService;
import com.roe.utils.MD5Utils;


public class UserServiceImpl implements UserService {
    private UserDao ud;
    @Override
    public User getUserByCodePassword(User user) {
        //根据登录名称查询登录用户
        User existU = ud.getByUserCode(user.getUser_code());
        //判断用户是否存在，不存在则抛出异常
        if(existU==null){
            throw new RuntimeException("用户名不存在");
        }
        //判断用户密码是否正确，不正确则抛出已成
        if(!existU.getUser_password().equals(user.getUser_password())){
            throw new RuntimeException("密码不存在");
        }

        //MD5加密比对
//        if(!existU.getUser_password().equals(MD5Utils.md5(user.getUser_password()))){
//            throw new RuntimeException("密码不存在");
//        }
        //返回查询到的对象
        return existU;
    }

    @Override
    public void saveUser(User u) {
        //1 调用Dao根据注册的登陆名获得用户对象
        User existU = ud.getByUserCode(u.getUser_code());
        if(existU!=null){
            //2 如果获得到user对象,用户名已经存在,抛出异常
            throw new RuntimeException("用户名已经存在!");
        }
        //使用MD5对密码进行加密
//        u.setUser_password(MD5Utils.md5(u.getUser_password()));
        //3 调用Dao执行保存
        ud.save(u);
    }
    public void setUd(UserDao ud) {
        this.ud = ud;
    }
}
