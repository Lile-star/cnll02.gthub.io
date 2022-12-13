package com.crm.workbench.clue.model;


import com.crm.workbench.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClueRemark {
    private Integer id;

    private String noteContent;

    private Integer createBy;

    private String createTime;

    private Integer editBy;

    private String editTime;

    private String editFlag;

    private Integer clueId;

    private User createUser;


}