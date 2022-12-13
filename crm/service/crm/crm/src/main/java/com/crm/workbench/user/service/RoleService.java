package com.crm.workbench.user.service;

import com.crm.commons.PageBean;

import java.util.List;
import java.util.Map;

public interface RoleService {
    PageBean getRoleList(Integer pageNumber, Integer pageSize);

    List<Map> getPermissionTreeDataByRoleId(Integer roleId);

    void disPerm(Integer roleId, String permIds);
}
