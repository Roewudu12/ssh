package com.roe.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.roe.domain.User;
import com.roe.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    private UserService us;

    public String login() throws Exception{
        //调用service执行登录逻辑
        User u = us.getUserByCodePassword(user);
        //将返回的User对象放入session域
        ActionContext.getContext().getSession().put("user",u);
        //重定向到项目首页
        return "toHome";
    }

    public String regist() throws Exception {
        //1 调用Service保存注册用户
        try {
            us.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            ActionContext.getContext().put("error", e.getMessage());
            return "regist";
        }
        //2 重定向到登陆页面
        return "toLogin";
    }

    public void setUs(UserService us) {
        this.us = us;
    }

    @Override
    public User getModel() {
        return user;
    }
}
