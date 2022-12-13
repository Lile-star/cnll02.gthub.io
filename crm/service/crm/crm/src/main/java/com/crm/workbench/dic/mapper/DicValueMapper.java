package com.crm.workbench.dic.mapper;

import com.crm.workbench.dic.model.DicValue;

public interface DicValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicValue record);

    int insertSelective(DicValue record);

    DicValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicValue record);

    int updateByPrimaryKey(DicValue record);
}