package com.crm.workbench.user.mapper;

import com.crm.workbench.user.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int countActivity();

    List<Map> selectRoleLimit(Integer skipNum, Integer pageSize);

    void insertPermission(Integer roleId, Integer permId);

    void deletePermissionByRoleId(Integer roleId);
}