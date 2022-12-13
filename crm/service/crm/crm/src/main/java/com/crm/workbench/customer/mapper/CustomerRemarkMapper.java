package com.crm.workbench.customer.mapper;

import com.crm.workbench.customer.model.CustomerRemark;

public interface CustomerRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerRemark record);

    int insertSelective(CustomerRemark record);

    CustomerRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerRemark record);

    int updateByPrimaryKey(CustomerRemark record);
}