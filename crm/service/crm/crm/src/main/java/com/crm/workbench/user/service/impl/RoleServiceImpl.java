package com.crm.workbench.user.service.impl;

import com.crm.commons.PageBean;
import com.crm.workbench.user.mapper.PermissionsMapper;
import com.crm.workbench.user.mapper.RoleMapper;
import com.crm.workbench.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionsMapper permissionsMapper;


    @Override
    public PageBean getRoleList(Integer pageNumber, Integer pageSize) {

        int total= roleMapper.countActivity();
        PageBean pageBean=new PageBean(pageNumber, pageSize,total);
        List<Map> maps = roleMapper.selectRoleLimit(pageBean.getSkipNum(), pageBean.getPageSize());
        pageBean.setData(maps);
        return pageBean;
    }

    @Override
    public List<Map> getPermissionTreeDataByRoleId(Integer roleId) {

        List<Map> list=permissionsMapper.selectPermissionTreeDataByRoleId(roleId);
        return list;
    }

    @Override
    public void disPerm(Integer roleId, String permIds) {
        roleMapper.deletePermissionByRoleId(roleId);
        if ("".equals(permIds)){
            return;
        }


        String[] split = permIds.split(",");
        for (String permId : split) {
            roleMapper.insertPermission(roleId,Integer.valueOf(permId));
        }
    }
}
