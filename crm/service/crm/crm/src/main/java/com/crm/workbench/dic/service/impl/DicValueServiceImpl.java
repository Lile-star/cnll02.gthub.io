package com.crm.workbench.dic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.crm.workbench.dic.mapper.DicTypeMapper;
import com.crm.workbench.dic.mapper.DicValueMapper;
import com.crm.workbench.dic.model.DicType;
import com.crm.workbench.dic.service.DicValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DicValueServiceImpl implements DicValueService {
    @Resource
    private DicTypeMapper dicTypeMapper;


    @Override
    public Map getDicValueAll() {
       List<DicType> dicType1= dicTypeMapper.selectDicTypeAll();
       Map map=new HashMap();
        dicType1.forEach(dicType ->
            map.put(dicType.getCode(), JSONObject.toJSONString(dicType.getDicValueList())));
       return map;
    }
}
