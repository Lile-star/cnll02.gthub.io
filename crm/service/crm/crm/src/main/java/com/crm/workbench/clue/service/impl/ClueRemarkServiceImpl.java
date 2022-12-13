package com.crm.workbench.clue.service.impl;

import com.crm.workbench.clue.mapper.ClueRemarkMapper;
import com.crm.workbench.clue.model.ClueRemark;
import com.crm.workbench.clue.service.ClueRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Resource
    ClueRemarkMapper clueRemarkMapper;
    @Override
    public void addRemark(ClueRemark clueRemark) {
        clueRemarkMapper.insertSelective(clueRemark);
    }
}
