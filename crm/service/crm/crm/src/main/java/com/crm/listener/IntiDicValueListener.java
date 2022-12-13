package com.crm.listener;

import com.crm.workbench.dic.service.DicValueService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

@Component
public class IntiDicValueListener implements ServletContextListener {
    @Resource
    private DicValueService dicValueService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map dicValueMap=dicValueService.getDicValueAll();
        stringRedisTemplate.opsForHash().putAll("dicValue",dicValueMap);
        System.out.println("tomcat开启了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("tomcat关闭了");

    }
}
