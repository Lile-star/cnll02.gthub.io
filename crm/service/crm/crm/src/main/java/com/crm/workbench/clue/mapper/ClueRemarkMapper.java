package com.crm.workbench.clue.mapper;

import com.crm.workbench.clue.model.ClueRemark;

public interface ClueRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClueRemark record);

    int insertSelective(ClueRemark record);

    ClueRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClueRemark record);

    int updateByPrimaryKey(ClueRemark record);

    void deleteRenarkByClueId(Integer clueId);
}