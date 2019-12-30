package com.roe.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.roe.domain.Customer;
import com.roe.domain.LinkMan;
import com.roe.domain.SaleVisit;
import com.roe.domain.User;
import com.roe.service.SaleVisitService;
import com.roe.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
    private SaleVisit saleVisit = new SaleVisit();
    private SaleVisitService svs;

    //使用属性驱动接受分页信息
    private Integer currentPage;
    private Integer pageSize;
    public String list() throws Exception{
        //封装离线查询对象（为了今后代码的复用性）
        DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
        if(saleVisit.getCustomer()!=null && saleVisit.getCustomer().getCust_id()!=null){
            dc.add(Restrictions.eq("customer.cust_id",saleVisit.getCustomer().getCust_id()));
        }
        //Servcie查询分页数据
        PageBean pb = svs.getPageBean(dc,currentPage,pageSize);
        System.out.println(pb.getList().get(0));
        //将PageBean放入request域，转发到页面显示
        ActionContext.getContext().put("pageBean",pb);
        return "list";
    }

    public String add() throws Exception {
        //取出登录用户，放入SaleVisit实体，表达关系
        User user = (User) ActionContext.getContext().getSession().get("user");
        saleVisit.setUser(user);
        //调用Service保存客户拜访记录
        svs.save(saleVisit);
        //重定向到拜访记录列表
        return "toList";
    }

    public String delete() throws Exception {

        //调用Services删除客户拜访记录
        svs.delete(saleVisit);
        //重定向到拜访记录列表
        return "toList";
    }

    public String toEdit() throws Exception{
        //调用Service，查询LinkMan
        SaleVisit sv = svs.getById(saleVisit.getVisit_id());
        //将查询的LinkMan对象放入request域,转发到添加页面
        ActionContext.getContext().put("saleVisit",sv);

        return "add";
    }

    public void setSvs(SaleVisitService svs) {
        this.svs = svs;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }
}
