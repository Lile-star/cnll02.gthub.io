package com.crm.workbench.activity.service;

import com.crm.commons.PageBean;
import com.crm.workbench.activity.model.Activity;

import java.util.List;
import java.util.Map;


public interface ActivityService {

    PageBean getActivityList(Integer pageNumber, Integer pageSize, String name, String uname, String startDate, String endDate);

    Activity getActivityInfo(Integer activityId);


    void updateActivity(Activity activity);

    int deleteById(Integer activityId);

    void addActivity(Activity activity);

    Activity getActivityDetail(Integer activityId);

    List<Map> queryActivityByName(String activityName);

    Map getChartsCountActivityData();
}
