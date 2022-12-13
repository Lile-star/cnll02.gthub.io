package com.crm.workbench.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String loginAct;

    private String name;

    private String loginPwd;

    private String email;

    private String expireTime;

    private Integer lockState;

    private String deptNo;

    private String createTime;

    private Integer createBy;

    private String editTime;

    private Integer editBy;

    private List<String> urlList;


}