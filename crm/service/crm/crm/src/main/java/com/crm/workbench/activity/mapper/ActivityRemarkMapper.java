package com.crm.workbench.activity.mapper;

import com.crm.workbench.activity.model.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

    void deleteRemarkByActivityId(Integer activityId);

    List<ActivityRemark> selectActivityRemarkByActivityId(Integer activityId);
}