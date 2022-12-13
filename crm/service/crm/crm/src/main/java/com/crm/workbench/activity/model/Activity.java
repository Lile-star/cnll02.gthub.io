package com.crm.workbench.activity.model;

import com.crm.workbench.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Integer id;

    private Integer owner;

    private String name;

    private String startDate;

    private String endDate;

    private String cost;

    private String description;

    private String createTime;

    private Integer createBy;

    private String editTime;

    private Integer editBy;

    private User createByUser;

    private User editByUser;

    private List<ActivityRemark> activityRemarkList=new ArrayList<>();

}