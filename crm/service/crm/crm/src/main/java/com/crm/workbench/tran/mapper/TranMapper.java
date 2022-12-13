package com.crm.workbench.tran.mapper;

import com.crm.workbench.tran.model.Tran;

public interface TranMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tran record);

    int insertSelective(Tran record);

    Tran selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tran record);

    int updateByPrimaryKey(Tran record);
}