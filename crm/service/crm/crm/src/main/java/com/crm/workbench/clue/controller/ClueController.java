package com.crm.workbench.clue.controller;

import com.crm.commons.DateUtils;
import com.crm.commons.PageBean;
import com.crm.commons.ReturnObject;
import com.crm.commons.UserUtils;
import com.crm.workbench.activity.service.ActivityService;
import com.crm.workbench.clue.model.Clue;
import com.crm.workbench.clue.service.ClueService;
import com.crm.workbench.user.model.User;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.SpanIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@RestController
@CrossOrigin
public class ClueController {
    @Resource
    private ClueService clueService;

    @Resource
    private UserUtils userUtils;

    @RequestMapping("/workbench/clue/getClueList")
    public Object getClueList(Integer pageNumber, Integer pageSize, String fullName, String company, String phone, Integer source, String owner, String mphone, Integer clueState) {
        PageBean pageBean = clueService.getClueList(pageNumber, pageSize, fullName, company, phone, source, owner, mphone, clueState);
        return ReturnObject.SUCCESS(pageBean);
    }

    @RequestMapping("/workbench/clue/addClue")
    public Object addClue(Clue clue, String token) {
        User user = userUtils.getUserByTokenFromRedis(token);
        if (user == null) {
            return ReturnObject.ERROR(2, "请登陆后操作");
        }
        clue.setCreateBy(user.getId());
        clue.setCreateTime(DateUtils.nowDateTimeToString());
        clue.setOwner(user.getId());
        clueService.addClue(clue);

        return ReturnObject.SUCCESS();
    }

    @RequestMapping("/workbench/clue/deleteClue")
    public Object deleteClue(String clueIds) {
        clueService.deleteClueByIds(clueIds);
        return ReturnObject.SUCCESS();
    }

    @RequestMapping("/workbench/clue/getClueInfoById")
    public Object getClueInfoById(Integer clueId) {
        Clue clue = clueService.getClueInfoById(clueId);
        return ReturnObject.SUCCESS(clue);
    }

    @RequestMapping("/workbench/clue/update")
    public Object update(Clue updateClue, String token) {
        User user = userUtils.getUserByTokenFromRedis(token);
        if (!userUtils.islogin(token)) {
            return ReturnObject.ERROR(2, "请登录");
        }
        updateClue.setEditBy(user.getId());
        updateClue.setEditTime(DateUtils.nowDateTimeToString());
        clueService.update(updateClue);
        return ReturnObject.SUCCESS();
    }

    @RequestMapping("/workbench/clue/getClueDetail")
    public Object getClueDetail(Integer clueId,String token) {
        System.out.println(token);
        Clue clue = clueService.getClueDDetailById(clueId);
        return ReturnObject.SUCCESS(clue);
    }

    @RequestMapping("/workbench/clue/relationActivity")
    public Object relationActivity(String activityIds, Integer clueId) {
        clueService.relationActivity(activityIds, clueId);
        return ReturnObject.SUCCESS();
    }

    @RequestMapping("/workbench/clue/deleteActivityRelation")
    public Object deleteActivityRelation(Integer relationId) {
        clueService.deleteActivityRelation(relationId);
        return ReturnObject.SUCCESS();
    }

    @RequestMapping("/workbench/clue/getClueConvertInfo")
    public Object getClueConvertInfo(Integer clueId) {
        Map map = clueService.getClueConvertInfo(clueId);
        return ReturnObject.SUCCESS(map);
    }

    @RequestMapping("/workbench/clue/clueConvert")
    public Object clueConvert(String token, Integer clueId,Integer  activityId, String money, String name, String expectedDate, Integer stage){
        System.out.println(activityId);
        System.out.println(clueId+"clueid");
        User user = userUtils.getUserByTokenFromRedis(token);
        if (user==null){
            return ReturnObject.ERROR(2, "请重新登录");
        }
        clueService.clueConvert(user.getId(),clueId,activityId,money,name,expectedDate,stage);

        return ReturnObject.SUCCESS();
    }

}
