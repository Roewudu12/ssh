package com.roe.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.roe.domain.LinkMan;
import com.roe.service.LinkManService;
import com.roe.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();
    private LinkManService lms;

    //使用属性驱动接受分页信息
    private Integer currentPage;
    private Integer pageSize;
    public String list() throws Exception{
        //封装离线查询对象（为了今后代码的复用性）
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
        if(StringUtils.isNotBlank(linkMan.getLkm_name())){
            dc.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
        }
        if(linkMan.getCustomer()!=null && linkMan.getCustomer().getCust_id()!=null){
            dc.add(Restrictions.eq("customer.cust_id",linkMan.getCustomer().getCust_id()));
        }
        //Servcie查询分页数据
        PageBean pb = lms.getPageBean(dc,currentPage,pageSize);
        System.out.println(pb.getList().get(0));
        //将PageBean放入request域，转发到页面显示
        ActionContext.getContext().put("pageBean",pb);
        return "list";
    }

    public String add() throws Exception {
        //调用Service
        lms.save(linkMan);
        //重定向到联系人列表
        return "toList";
    }
    public String delete() throws Exception {
        //调用Service
        lms.delete(linkMan);
        //重定向到联系人列表
        return "toList";
    }

    public String toEdit() throws Exception{
        //调用Service，查询LinkMan
        LinkMan lm = lms.getById(linkMan.getLkm_id());
        //将查询的LinkMan对象放入request域,转发到添加页面
        ActionContext.getContext().put("linkMan",lm);

        return "add";
    }


    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    public void setLms(LinkManService lms) {
        this.lms = lms;
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
}
