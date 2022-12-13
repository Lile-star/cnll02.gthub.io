package com.crm.workbench.activity.service.impl;

import com.crm.commons.PageBean;
import com.crm.workbench.activity.mapper.ActivityMapper;
import com.crm.workbench.activity.mapper.ActivityRemarkMapper;
import com.crm.workbench.activity.model.Activity;
import com.crm.workbench.activity.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private ActivityRemarkMapper activityRemarkMapper;

    /**
     * f分页获取市场活动
     * @param pageNumber
     * @param pageSize
     * @param name
     * @param uname
     * @param startDate
     * @param endDate
     * @return
     * 分页中要的数字
     * pageNumber 当前页码(第几页) 来自前端
     * pageSize   每页可现实的记录数量,可以来自前端或项目的静态常量
     * total 总记录数量, 利用SQL的count语句统计的
     * pageCount 总页数,通过计算获得 pageCount=total/pageSize 如果 total/pageSize有余数 则pageCount+1
     * skipNum 跳过的记录数量,通过计算获得skipNum=(pageNumber-1)*pageSize
     */
    @Override
    public PageBean getActivityList(Integer pageNumber, Integer pageSize, String name, String uname, String startDate, String endDate) {
     int total= activityMapper.countActivity(name,uname,startDate,endDate);
        PageBean pageBean=new PageBean(pageNumber, pageSize,total);
        List<Map> maps = activityMapper.selectActivityLimit(pageBean.getSkipNum(), pageBean.getPageSize(),name,uname,startDate,endDate);
        pageBean.setData(maps);
        return pageBean;
    }

    @Override
    public Activity getActivityInfo(Integer activityId) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        return activity;
    }

    @Override
    public void updateActivity(Activity activity) {
        activityMapper.updateActivity(activity);
    }

    @Override
    @Transactional
    public int deleteById(Integer activityId) {
        try {
            activityRemarkMapper.deleteRemarkByActivityId(activityId);
            System.out.println(activityId+"这时删除功能");
            activityMapper.deleteByPrimaryKey(activityId);
        } catch (Exception e) {
            System.out.println(e.getClass());
            return 1;
        }
        return 0;
    }

    @Override
    public void addActivity(Activity activity) {
        activityMapper.insertSelective(activity);
    }

    @Override
    public Activity getActivityDetail(Integer activityId) {
        Activity activity = activityMapper.selectActivityDetailById(activityId);
       // List<ActivityRemark> activityRemarkList=activityRemarkMapper.selectActivityRemarkByActivityId(activityId);
       // activity.setActivityRemarkList(activityRemarkList);
        return  activity;
    }

    @Override
    public List<Map> queryActivityByName(String activityName) {

        List<Map>  likeActivity=  activityMapper.selectActivityLikeActivityName(activityName);

        return likeActivity;
    }

    @Override
    public Map getChartsCountActivityData() {
        Map map=new HashMap();
        List xdata=new ArrayList();
        List ydata=new ArrayList();
        List<Map> data = activityMapper.getChartsCountActivityData();
        for (Map datum : data) {
            xdata.add(datum.get("name"));
            ydata.add(datum.get("cost"));
        }
        map.put("xdata", xdata);
        map.put("ydata", ydata);
        System.out.println(map);
        return map;
    }
}
