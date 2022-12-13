package com.crm.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;
    private Integer pageCount;
    private Integer skipNum;
    //分页时具体获取的对象数据
    private List<T> data;

    public PageBean(Integer pageNumber, Integer pageSize, Integer total) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.pageCount=total/pageSize;
        if (total%pageSize>0){
            pageCount++;
        }
        this.skipNum=(pageNumber-1)*pageSize;

    }
}
