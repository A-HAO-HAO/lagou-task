package com.cuihao.blog.sbblog.util;

import java.util.List;

public class Page<T> {

    private Integer pageNo = 1;

    private Integer pageSize = 1;

    private List<T> dataList;

    private Long totoalCount;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Long getTotoalCount() {
        return totoalCount;
    }

    public void setTotoalCount(Long totoalCount) {
        this.totoalCount = totoalCount;
    }

    public Long getTotalPage(){
        if(totoalCount != null){
            Long totalPage = totoalCount/pageSize;
            if(totoalCount%pageSize > 0){
                totalPage++;
            }
            return totalPage;
        }
        return null;
    }

    public Integer getOffset(){
        return (pageNo-1)*pageSize;
    }
}
