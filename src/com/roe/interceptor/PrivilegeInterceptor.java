package com.roe.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.roe.domain.User;

import java.util.Map;

public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    //不校验登录和注册方法
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //获得session
        Map<String, Object> session = ActionContext.getContext().getSession();
        //获得登录标识
        User user = (User) session.get("user");
        //判断标识是否存在
        if(user != null){
            return actionInvocation.invoke();
        }else {
            return "toLogin";
        }
    }
}
