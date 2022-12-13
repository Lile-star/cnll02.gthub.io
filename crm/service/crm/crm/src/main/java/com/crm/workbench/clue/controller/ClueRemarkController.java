package com.crm.workbench.clue.controller;


import com.crm.commons.DateUtils;
import com.crm.commons.ReturnObject;
import com.crm.commons.UserUtils;
import com.crm.workbench.clue.model.ClueRemark;
import com.crm.workbench.clue.service.ClueRemarkService;
import com.crm.workbench.user.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class ClueRemarkController {

    @Resource
    private ClueRemarkService clueRemarkService;
    @Resource
    private UserUtils userUtils;

    @RequestMapping("/workbench/clueRemark/addRemark")
    public Object addRemark(Integer clueId,String remarkContent,String token){
        User user = userUtils.getUserByTokenFromRedis(token);
        if (!userUtils.islogin(token)){
            return ReturnObject.ERROR(2, "请重新登录");
        }
        ClueRemark clueRemark=new ClueRemark();
        clueRemark.setClueId(clueId);
        clueRemark.setNoteContent(remarkContent);
        clueRemark.setCreateBy(user.getId());
        clueRemark.setCreateTime(DateUtils.nowDateTimeToString());
        clueRemarkService.addRemark(clueRemark);
        return ReturnObject.SUCCESS();
    }
}
