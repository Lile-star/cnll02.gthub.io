package com.crm.workbench.clue.service.impl;

import com.crm.commons.DateUtils;
import com.crm.commons.PageBean;
import com.crm.workbench.clue.mapper.ClueMapper;
import com.crm.workbench.clue.mapper.ClueRemarkMapper;
import com.crm.workbench.clue.model.Clue;
import com.crm.workbench.clue.service.ClueService;
import com.crm.workbench.customer.mapper.ContactsMapper;
import com.crm.workbench.customer.mapper.CustomerMapper;
import com.crm.workbench.customer.model.Contacts;
import com.crm.workbench.customer.model.Customer;
import com.crm.workbench.tran.mapper.TranHistoryMapper;
import com.crm.workbench.tran.mapper.TranMapper;
import com.crm.workbench.tran.model.Tran;
import com.crm.workbench.tran.model.TranHistory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
 public class ClueServiceImpl implements ClueService {

    @Resource
    ClueMapper clueMapper;

    @Resource
    ClueRemarkMapper clueRemarkMapper;

    @Resource
    CustomerMapper customerMapper;

    @Resource
    ContactsMapper contactsMapper;

    @Resource
    TranMapper tranMapper;

    @Resource
    TranHistoryMapper tranHistoryMapper;




    @Override
    public PageBean getClueList(Integer pageNumber, Integer pageSize, String fullName, String company, String phone, Integer source, String owner, String mphone, Integer clueState) {
        int total= clueMapper.countClue(fullName,company,phone,source,owner,mphone,clueState);
        PageBean pageBean=new PageBean(pageNumber, pageSize,total);
        List<Map> maps = clueMapper.selectClueLimit(pageBean.getSkipNum(), pageBean.getPageSize(),fullName,company,phone,source,owner,mphone,clueState);
        pageBean.setData(maps);
        return pageBean;
    }

    @Override
    public void addClue(Clue clue) {
        clueMapper.insert(clue);
    }

    @Override
    @Transactional
    public void deleteClueByIds(String clueIds) {
        String[] clueidsArr = clueIds.split(",");
        for (String clueid : clueidsArr) {
            //???????????????id???????????????????????????
            clueRemarkMapper.deleteByPrimaryKey(Integer.valueOf(clueid));
            //???????????????id??????????????????????????????????????????????????????
            clueMapper.deleteClueActivityRelationByClueId(Integer.valueOf(clueid));
            //???????????????id??????????????????
            clueMapper.deleteByPrimaryKey(Integer.valueOf(clueid));
        }

    }

    @Override
    public Clue getClueInfoById(Integer clueId) {
        Clue clue=clueMapper.getClueInfoById(clueId);
        return clue;
    }

    @Override
    public void update(Clue updateClue) {
        clueMapper.updateByPrimaryKeySelective(updateClue);
    }

    @Override
    public Clue getClueDDetailById(Integer clueId) {
        Clue clue=clueMapper.selectClueDetailById(clueId);
                return clue;

    }

    @Override
    public void relationActivity(String activityIds, Integer clueId) {
        String[] strings = activityIds.split(",");
        for (String actId : strings) {
            try {
                clueMapper.relationActivity(actId,clueId);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteActivityRelation(Integer relationId) {
        clueMapper.deleteActivityRelation(relationId);
    }

    @Override
    public Map getClueConvertInfo(Integer clueId) {
        return clueMapper.getClueConvertInfo(clueId);
    }



    @Override
    @Transactional
    public int clueConvert(Integer userId, Integer clueId, Integer activityId, String money, String name, String expectedDate, Integer stage) {
        Clue clue = clueMapper.selectByPrimaryKey(clueId);
        clueMapper.deleteClueActivityRelationByClueId(clueId);
        clueRemarkMapper.deleteRenarkByClueId(clueId);
        clueMapper.deleteByPrimaryKey(clueId);



            //???????????????????????????????????????
        Customer customer=new Customer();
        //????????????
        customer.setAddress(clue.getAddress());
        //???????????????
        customer.setCreateBy(userId);
        //??????????????????
        customer.setCreateTime(DateUtils.nowDateTimeToString());
        //??????
        customer.setDescription(clue.getDescription());
        //???????????????
        customer.setContactSummary(clue.getContactSummary());
        //?????????
        customer.setOwner(userId);
        //??????
        customer.setPhone(clue.getPhone());
        //????????????
        customer.setName(clue.getCompany());
        //????????????
        customer.setWebsite(clue.getWebsite());
        //???????????????
        customerMapper.insert(customer);





        //??????????????????????????????????????????
        Contacts contacts=new Contacts();

        //??????
        contacts.setAddress(clue.getAddress());
        //??????
        contacts.setAppellation(clue.getAppellation());
        //???????????????
        contacts.setContactSummary(clue.getContactSummary());
        //?????????
        contacts.setCreateBy(userId);
        //????????????
        contacts.setCreateTime(DateUtils.nowDateTimeToString());
        //??????id
        contacts.setCustomerId(customer.getId());
        //??????
        contacts.setDescription(clue.getDescription());
        //???????????????
        contacts.setEmail(clue.getEmail());
        //???????????????
        contacts.setFullName(clue.getFullName());
        //???????????????
        contacts.setJob(clue.getJob());
        //?????????????????????
        contacts.setMphone(clue.getMphone());
        //??????????????????
        contacts.setOwner(clue.getOwner());
        //???????????????
        contacts.setSource(clue.getSource());
        //???!
        contactsMapper.insert(contacts);
        //???????????????????????????????????????
           contactsMapper.insertContactsActivityRelation(contacts.getId(),activityId);



           //????????????????????????????????????
        Tran tran=new Tran();
        tran.setActivityId(activityId);
        tran.setContactsId(contacts.getId());
        tran.setCreateBy(userId);
        tran.setCreateTime(DateUtils.nowDateTimeToString());
        tran.setOwner(userId);
        tran.setCustomerId(customer.getId());
        tran.setDescription("???????????????");
        tran.setExpectedDate(expectedDate);
        tran.setMoney(money);
        tran.setOrderNo(UUID.randomUUID().toString());
        tran.setSource(clue.getSource());
        tran.setType(46);
        tran.setName(name);
        //??????????????????
        tranMapper.insertSelective(tran);

        //?????????????????????????????????????????????
        TranHistory tranHistory=new TranHistory();
        tranHistory.setCreateBy(userId);
        tranHistory.setCreateTime(DateUtils.nowDateTimeToString());
        tranHistory.setExpectedDate(expectedDate);
        tranHistory.setMoney(money);
        tranHistory.setOrderNo(1);//?????????
        tranHistory.setStage(stage);
        tranHistory.setTranId(tran.getId());
        //4.4 ????????????????????????
        tranHistoryMapper.insert(tranHistory);
        return 0;
    }
}
