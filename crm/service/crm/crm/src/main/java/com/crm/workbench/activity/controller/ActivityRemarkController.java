package com.crm.workbench.activity.controller;

import com.crm.commons.DateUtils;
import com.crm.commons.ReturnObject;
import com.crm.commons.UserUtils;
import com.crm.workbench.activity.model.ActivityRemark;
import com.crm.workbench.activity.service.ActivityRemarkService;
import com.crm.workbench.user.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@CrossOrigin
public class ActivityRemarkController {
    @Resource
    private UserUtils userUtils;
    @Resource
    private ActivityRemarkService activityRemarkService;
    @RequestMapping("/workbench/activity/addActivityRemark")
    public Object addActivityRemark(Integer activityId,String noteContent,String token){
        User redis = userUtils.getUserByTokenFromRedis(token);
        if (redis==null){
            return ReturnObject.ERROR(2, "请登录后再操作");
        }
        ActivityRemark activityRemark=new ActivityRemark();
        activityRemark.setActivityId(activityId);
        activityRemark.setNoteContent(noteContent);
        activityRemark.setCreateBy(redis.getId());
        activityRemark.setCreateTime(DateUtils.nowDateTimeToString());
        activityRemarkService.addActivityRemark(activityRemark);
        return ReturnObject.SUCCESS();
    }

}
