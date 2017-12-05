package com.example.demo.base;

import java.io.Serializable;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.base
 * @Date: 2017/12/5
 * @Time: 14:41
 */
public class BaseEntity implements Serializable {

    //分页页码默认为1
    private int pageNo = 1;

    //分页页面展示条数默认10条
    private int pageSize = 10;

    //排序列名称默认为id
    private String sidx = "id";

    //排序类型,默认降序
    private String sort = "desc";

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }
}
