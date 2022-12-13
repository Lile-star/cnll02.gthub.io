package com.crm.workbench.clue.mapper;

import com.crm.workbench.clue.model.Clue;

import java.util.List;
import java.util.Map;

public interface ClueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

    List<Map> selectClueLimit(Integer skipNum, Integer pageSize, String fullName, String company, String phone, Integer source, String owner, String mphone, Integer clueState);

    int countClue(String fullName, String company, String phone, Integer source, String owner, String mphone, Integer clueState);

    void deleteClueActivityRelationByClueId(Integer clueid);

    Clue getClueInfoById(Integer clueId);

    Clue selectClueDetailById(Integer clueId);

    void relationActivity(String actId, Integer clueId);

    void deleteActivityRelation(Integer relationId);

    Map getClueConvertInfo(Integer clueId);
}