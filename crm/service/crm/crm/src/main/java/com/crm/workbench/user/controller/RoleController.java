package com.crm.workbench.user.controller;

import com.crm.commons.PageBean;
import com.crm.commons.ReturnObject;
import com.crm.workbench.user.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class RoleController {

    @Resource
    private RoleService roleService;


    @RequestMapping("workbench/role/getRoleList")
    public Object getRoleList(Integer pageNumber, Integer pageSize) {
        PageBean pageBean = roleService.getRoleList(pageNumber, pageSize);
        return ReturnObject.SUCCESS(pageBean);
    }

    @RequestMapping("/workbench/role/initPermissionTreeDataByRoleId")
    public Object initPermissionTreeDataByRoleId(Integer roleId) {
        List<Map> list = roleService.getPermissionTreeDataByRoleId(roleId);
        return list;
    }

    @RequestMapping("/workbench/role/disPerm")
    public Object disPerm(Integer roleId,String permIds){
        roleService.disPerm(roleId,permIds);
        return ReturnObject.SUCCESS();
    }

}
