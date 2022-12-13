package com.crm.interceptors;


import com.crm.commons.UserUtils;
import com.crm.workbench.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
     private UserUtils userUtils;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");

        User user = userUtils.getUserByTokenFromRedis(token);
        if (user==null){
            response.sendRedirect("/onLogin");
            return  false;
        }
        String path = request.getServletPath();
        Boolean aBoolean = stringRedisTemplate.opsForHash().hasKey("session:" + token, path);
        if (!aBoolean){
            response.sendRedirect("/noPermission");
            return false;
        }
        return true;
    }
}
