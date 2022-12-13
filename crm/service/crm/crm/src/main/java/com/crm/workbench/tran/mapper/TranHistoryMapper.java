package com.crm.workbench.tran.mapper;

import com.crm.workbench.tran.model.TranHistory;

public interface TranHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TranHistory record);

    int insertSelective(TranHistory record);

    TranHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TranHistory record);

    int updateByPrimaryKey(TranHistory record);
}