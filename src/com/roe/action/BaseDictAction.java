package com.roe.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.roe.domain.BaseDict;
import com.roe.domain.Customer;
import com.roe.service.BaseDictService;
import com.roe.service.CustomerService;
import com.roe.utils.PageBean;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BaseDictAction extends ActionSupport {

    private String dict_type_code;
    private BaseDictService baseDictService;
    @Override
    public String execute() throws Exception {
        //调用service根据typecode获取数字对象list
        List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
        //将list转化为json格式
        String json = JSONArray.fromObject(list).toString();
        //将json发送给浏览器
        //处理中文乱码
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return null;//告诉struts2不需要进行结果处理
    }

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }
}
