package com.roe.utils;

import java.util.List;

/**
 * 查询分页
 */
public class PageBean {
    private Integer currentPage;    //当前页数
    private Integer totalCount;     //总记录数
    private Integer pageSize;       //每页显示条数
    private Integer totalPage;      //总页数
    private List list;              //分页列表数据

    //创建时必须给的属性
    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.totalCount = totalCount;
        this.currentPage = currentPage; //可能为空
        this.pageSize = pageSize;       //可能为空

        if(this.currentPage == null){
            this.currentPage = 1;   //设置默认为1
        }

        if(this.pageSize == null){
            this.pageSize = 5;      //设置默认为5
        }

        //计算总页数(数学方法解决，加上临界值)
        this.totalPage = (this.totalCount+this.pageSize-1)/this.pageSize;
        //判断当前页数是否超出范围
        if(this.currentPage<1){
            this.currentPage = 1;
        }
        if(this.currentPage > this.totalPage){
            this.currentPage = this.totalPage;
        }
    }

    //计算起始索引
    public int getStart(){
       return (this.currentPage-1)*this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
