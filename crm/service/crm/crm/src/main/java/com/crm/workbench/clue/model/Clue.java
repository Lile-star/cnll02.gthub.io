package com.crm.workbench.clue.model;

import com.crm.workbench.dic.model.DicValue;
import com.crm.workbench.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clue {
    private Integer id;

    private String fullName;

    private Integer appellation;

    private Integer owner;

    private String company;

    private String job;

    private String email;

    private String phone;

    private String website;

    private String mphone;

    private Integer state;

    private Integer source;

    private Integer createBy;

    private String createTime;

    private Integer editBy;

    private String editTime;

    private String description;

    private String contactSummary;

    private String nextContactTime;

    private String address;

    //数据字典多对一封装
    private DicValue appellationDicValue;
    private DicValue stateDicValue;
    private DicValue sourceDicValue;
    //所有者 创建者 修改者的多对一映射
    private User ownerUser;
    private User createByUser;
    private User editByUser;

    private List<ClueRemark> clueRemarkList=new ArrayList<>();

    private List<Map> activityList=new ArrayList<>();


}