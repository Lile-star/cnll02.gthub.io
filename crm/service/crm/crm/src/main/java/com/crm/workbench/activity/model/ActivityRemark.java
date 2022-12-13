package com.crm.workbench.activity.model;

import com.crm.workbench.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRemark {
    private Integer id;

    private String noteContent;

    private String createTime;

    private Integer createBy;

    private String editTime;

    private Integer editBy;

    private String editFlag;

    private Integer activityId;

    private User createByUser;


}