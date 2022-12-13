package com.crm.commons;

import com.alibaba.fastjson.JSONObject;
import com.crm.workbench.user.model.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

@Component
public class UserUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public  User getUserByTokenFromRedis(String token){
       String userSession = (String) stringRedisTemplate.opsForHash().get("session:" + token, "userSession");
        stringRedisTemplate.expire("session:" + token, Duration.ofSeconds(60 * 30));
        return JSONObject.parseObject(userSession,User.class);
    }
    public boolean islogin(String token){
        User user = getUserByTokenFromRedis(token);
        if (user==null){
            return false;
        }
        return true;
    }

}
