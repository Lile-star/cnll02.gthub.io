package com.crm.workbench.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.crm.commons.ReturnObject;
import com.crm.commons.UserUtils;
import com.crm.workbench.user.model.User;
import com.crm.workbench.user.service.UserService;
import com.crm.workbench.user.service.impl.UserServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserUtils userUtils;

    @RequestMapping("/login")
    public Object login(User user) {
        //用户登录返回登陆结果,0为登录成功,1为登陆失败

        int result = userService.login(user);

        if (result == 1) {
            return ReturnObject.ERROR(1, "账号不存在");
        } else if (result == 2) {
            return ReturnObject.ERROR(2, "密码不正确");
        } else if (result == 3) {
            return ReturnObject.ERROR(3, "账号被锁定");
        } else if (result == 4) {
            return ReturnObject.ERROR(4, "账号已过期");
        }

        List<String> urlList=user.getUrlList();
        user.setUrlList(null);


        //令牌
        //存入redis hash,其中token作为key
        //使用用户对象的json数据作为最终的数据,使用字符串userSession作为hash中的字段名称
        String token = UUID.randomUUID().toString();
        String userJson = JSONObject.toJSONString(user);


        stringRedisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                redisOperations.multi();
                //存入redis hash,其中token作为key
                //使用用户对象的json数据作为最终的数据,使用字符串userSession作为hash中的字段名称
                redisOperations.opsForHash().put((K) ("session:" + token), "userSession", userJson);
                    for (String url : urlList) {
                        redisOperations.opsForHash().put((K) ("session:" + token), url, "李乐");
                    }
                redisOperations.expire((K) ("session:" + token), Duration.ofSeconds(60 * 30));
                return redisOperations.exec();
            }
        });
//        Map map=new HashMap();
//        //进入if表示用户点击了自动登录,需要通知浏览器将账号密码保持
//        //应该将该账号的账号密码返回前端,如果非前后端分离项目,用户点击自动登录,必须在后端进入if后将数据回写给浏览器的cookie
//        if (isAutLogin){
//            map.put("loginAct", user.getLoginAct());
//            map.put("loginPwd", user.getLoginPwd());
//            map.put("token", token);
//        }
        //登陆成功后返回响应成功,将token(令牌返回前端)
        //由后台返回账号密码.前段保存数据实现自动登录,也可以不由后台返回,如果或用户的密码需要在后台进行二次加密,建议由后台返回数据

        return ReturnObject.SUCCESS(token);
    }


    @RequestMapping("/onLogin")
    public Object noLogin(){
        return ReturnObject.ERROR(2, "请登录");
    }

    @RequestMapping("/noPermission")
    public Object noPermission(){
        return ReturnObject.ERROR(1, "您没有权限,请找管理员");
    }
}
