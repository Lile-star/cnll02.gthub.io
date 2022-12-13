package com.crm.workbench.tran.mapper;

import com.crm.workbench.tran.model.TranRemark;

public interface TranRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TranRemark record);

    int insertSelective(TranRemark record);

    TranRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TranRemark record);

    int updateByPrimaryKey(TranRemark record);
}