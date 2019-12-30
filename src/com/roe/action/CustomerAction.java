package com.roe.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.roe.domain.Customer;
import com.roe.service.CustomerService;
import com.roe.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Customer customer = new Customer();
    private CustomerService cs;

    //使用属性驱动接受parameter中控制统计来源/行业的值
    private String industryOrSource;

    //使用属性驱动接受分页信息
    private Integer currentPage;
    private Integer pageSize;
    public String list() throws Exception{
        //封装离线查询对象（为了今后代码的复用性）
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        if(StringUtils.isNotBlank(customer.getCust_name())){
            dc.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));
        }
        //Servcie查询分页数据
        PageBean pb = cs.getPageBean(dc,currentPage,pageSize);
        System.out.println(pb.getList().get(0));
        //将PageBean放入request域，转发到页面显示
        ActionContext.getContext().put("pageBean",pb);
        return "list";
    }

    public String add() throws Exception {
        //直接调用Service，保存Customer对象
        cs.save(customer);
        //重定向到客户列表Action
        return "toList";
    }

    public String toEdit() throws Exception {
        //调用Service根据id获得客户对象
       Customer c = cs.getById(customer.getCust_id());
        //将对象放到域中
        ActionContext.getContext().put("customer",c);
        return "edit";
    }

    public String delete() throws Exception {
        //调用Service对象删除客户
        cs.delete(customer);
        //重定向到列表页面
        return "toList";
    }

    public String industryCount() throws Exception {
        List<Object[]> list = cs.getIndustryCount(industryOrSource);

        ActionContext.getContext().put("list",list);

        return "industryCount";
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    @Override
    public Customer getModel() {
        return customer;
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

    public String getIndustryOrSource() {
        return industryOrSource;
    }

    public void setIndustryOrSource(String industryOrSource) {
        this.industryOrSource = industryOrSource;
    }
}
