package com.crm.workbench.activity.service.impl;

import com.crm.workbench.activity.mapper.ActivityRemarkMapper;
import com.crm.workbench.activity.model.ActivityRemark;
import com.crm.workbench.activity.service.ActivityRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Resource
    ActivityRemarkMapper activityRemarkMapper;

    @Override
    public void addActivityRemark(ActivityRemark activityRemark) {
        activityRemarkMapper.insert(activityRemark);
    }
}
