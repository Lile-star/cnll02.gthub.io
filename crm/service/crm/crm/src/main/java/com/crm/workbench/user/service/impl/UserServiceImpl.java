package com.crm.workbench.user.service.impl;

import com.crm.workbench.user.mapper.PermissionsMapper;
import com.crm.workbench.user.mapper.RoleMapper;
import com.crm.workbench.user.mapper.UserMapper;
import com.crm.workbench.user.model.Permissions;
import com.crm.workbench.user.model.Role;
import com.crm.workbench.user.model.User;
import com.crm.workbench.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionsMapper permissionsMapper;

    @Override
    public int login(User user) {
        //索引调优,因为账号有唯一性索性查找更快
        User user1 = userMapper.selectUserByLoginAct(user.getLoginAct());

        //进入if表示该账号不存在,账号错误返回1
        if (user1 == null) {
            return 1;
        }
        //进入if表示数据中的密码和用户页面输入的密码不一致,密码错误返回2
        if (!user1.getLoginPwd().equals(user.getLoginPwd())) {
            return 2;
        }
        //进入if表示账号锁定  返回3
        if (user1.getLockState() != 1) {
            return 3;
        }
        //进入if表示当前系统时间大于过期时间,证明行号过期
        Date parse = null;
        try {
            parse = sf.parse(user1.getExpireTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(user1.getExpireTime());
        System.out.println(System.currentTimeMillis());
        if (parse.getTime() < System.currentTimeMillis()) {
            return 4;
        }
        BeanUtils.copyProperties(user1, user);
        System.out.println(user+"++++++++++++++++");

        List<String> urlList= permissionsMapper.selectPermissionUrlByUserId(user1.getId());
        user.setUrlList(urlList);
        //程序如都没有进入上方if,则表示登录成功,讲数据库中的user数据拷贝给参数对象
        return 0;
    }
}
