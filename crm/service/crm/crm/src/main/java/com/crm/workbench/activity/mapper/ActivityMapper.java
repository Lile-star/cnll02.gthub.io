package com.crm.workbench.activity.mapper;

import com.crm.workbench.activity.model.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Map> selectActivityLimit(Integer skipNum, Integer pageSize, String name, String uname, String startDate, String endDate);

    int countActivity(String name, String uname, String startDate, String endDate);

    void updateActivity(Activity activity);

    Activity selectActivityDetailById(Integer activityId);

    List<Map> selectActivityLikeActivityName(String activityName);

    List<Map> getChartsCountActivityData();
}