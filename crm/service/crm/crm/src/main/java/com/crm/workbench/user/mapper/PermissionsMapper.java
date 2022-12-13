package com.crm.workbench.user.mapper;

import com.crm.workbench.user.model.Permissions;

import java.util.List;
import java.util.Map;

public interface PermissionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);

    List<String> selectPermissionUrlByUserId(Integer id);

    List<Map> selectPermissionTreeDataByRoleId(Integer roleId);
}