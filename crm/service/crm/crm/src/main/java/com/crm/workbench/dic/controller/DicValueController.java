package com.crm.workbench.dic.controller;

import com.alibaba.fastjson.JSONObject;
import com.crm.commons.ReturnObject;
import com.crm.workbench.dic.model.DicValue;
import com.crm.workbench.dic.service.DicValueService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class DicValueController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/workbench/dic/getDivValueByTypeCodeFromRedis")
    public Object getDivValueByTypeCodeFromRedis(String typeCodes){
        String[] typecodeArr = typeCodes.split(",");
        Map map=new HashMap();
        for (String typecode : typecodeArr) {
            Object dicValue = stringRedisTemplate.opsForHash().get("dicValue", typecode);
                map.put(typecode, JSONObject.parseArray(dicValue.toString(), DicValue.class));
        }
    return ReturnObject.SUCCESS(map);
    }


}
