package com.crm.workbench.clue.service;

import com.crm.commons.PageBean;
import com.crm.workbench.clue.model.Clue;

import java.util.Map;

public interface ClueService {
    PageBean getClueList(Integer pageNumber, Integer pageSize, String fullName, String company, String phone, Integer source, String owner, String mphone, Integer clueState);

    void addClue(Clue clue);

    void deleteClueByIds(String clueIds);

    Clue getClueInfoById(Integer clueId);

    void update(Clue updateClue);

    Clue getClueDDetailById(Integer clueId);

    void relationActivity(String activityIds, Integer clueId);

    void deleteActivityRelation(Integer relationId);

    Map getClueConvertInfo(Integer clueId);

    int clueConvert(Integer userId, Integer clueId, Integer activityId, String money, String name, String expectedDate, Integer stage);
}
