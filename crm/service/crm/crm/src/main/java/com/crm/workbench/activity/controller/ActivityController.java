package com.crm.workbench.activity.controller;

import com.crm.commons.DateUtils;
import com.crm.commons.PageBean;
import com.crm.commons.ReturnObject;
import com.crm.commons.UserUtils;
import com.crm.workbench.activity.model.Activity;
import com.crm.workbench.activity.service.ActivityService;
import com.crm.workbench.user.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ActivityController {
    @Resource
    private ActivityService activityService;
@Resource
private UserUtils userUtils;

    @RequestMapping("/workbench/activity/getActivityList")
    public Object getActivityList(Integer pageNumber, Integer pageSize, String name, String uname, String startDate, String endDate) {
        PageBean pageBean= activityService.getActivityList(pageNumber,pageSize, name,uname,startDate,endDate);
        return ReturnObject.SUCCESS(pageBean);
    }
    //点击修改返回到修改操作页面
    @RequestMapping("/workbench/activity/getActivityInfo")
    public Object getActivityInfo(Integer activityId){
        Activity activityInfo = activityService.getActivityInfo(activityId);
        return ReturnObject.SUCCESS(activityInfo);
    }
    //修改页面操作
    @RequestMapping("/workbench/activity/update")
    public Object update(Activity activity,String token){
        User user = userUtils.getUserByTokenFromRedis(token);
        if (user==null){
            return ReturnObject.ERROR(2, "请登录后在操作");
        }
        activity.setEditBy(user.getId());
        activity.setEditTime(DateUtils.nowDateTimeToString());
       activityService.updateActivity(activity);
        return ReturnObject.SUCCESS(activity);
    }
    //根据主键删除
    @RequestMapping("/workbench/activity/deleteActivityById")
    public Object deleteActivityById(Integer activityId,String token){
        int i = activityService.deleteById(activityId);
        if (i!=0){
            return ReturnObject.ERROR(1, "请删除和当前活动相关");
        }
        return ReturnObject.SUCCESS();
    }
    @RequestMapping("/workbench/activity/addActivity")
    public Object addActivity(Activity activity,String token){
        User user = userUtils.getUserByTokenFromRedis(token);
        if (!userUtils.islogin(token)){
            return ReturnObject.ERROR(2, "请登录后操作");
        }
        activity.setCreateBy(user.getId());
        activity.setOwner(user.getId());
        activity.setCreateTime(DateUtils.nowDateTimeToString());
        activityService.addActivity(activity);
        return ReturnObject.SUCCESS();
    }
    @RequestMapping("/workbench/activity/getActivityDetail")
    public Object getActivityDetail(Integer activityId){
        Activity activity=activityService.getActivityDetail(activityId);
        return ReturnObject.SUCCESS(activity);
    }
    @RequestMapping("/workbench/activity/queryActivityByName")
    public Object queryActivityByName(String activityName){
       List<Map> activityList= activityService.queryActivityByName(activityName);
       return ReturnObject.SUCCESS(activityList);
    }

    @RequestMapping("/workbench/activity/getChartsCountActivityData")
    public Object getChartsCountActivityData(){
        Map map=  activityService.getChartsCountActivityData();
        return ReturnObject.SUCCESS(map);
    }







}
