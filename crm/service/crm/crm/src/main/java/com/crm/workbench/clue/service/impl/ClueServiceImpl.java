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
            //根据线索的id删除线索的备注信息
            clueRemarkMapper.deleteByPrimaryKey(Integer.valueOf(clueid));
            //根据线索的id删除线索和市场活动的多对多的关联数据
            clueMapper.deleteClueActivityRelationByClueId(Integer.valueOf(clueid));
            //根据线索的id删除线索主键
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



            //封装客户信息对象添加到表中
        Customer customer=new Customer();
        //客户地址
        customer.setAddress(clue.getAddress());
        //客户创建者
        customer.setCreateBy(userId);
        //客户创建时间
        customer.setCreateTime(DateUtils.nowDateTimeToString());
        //描述
        customer.setDescription(clue.getDescription());
        //联系人总结
        customer.setContactSummary(clue.getContactSummary());
        //所有者
        customer.setOwner(userId);
        //电话
        customer.setPhone(clue.getPhone());
        //公司名称
        customer.setName(clue.getCompany());
        //公司网站
        customer.setWebsite(clue.getWebsite());
        //进入持久层
        customerMapper.insert(customer);





        //封装客户联系人对象添加到表中
        Contacts contacts=new Contacts();

        //地址
        contacts.setAddress(clue.getAddress());
        //名号
        contacts.setAppellation(clue.getAppellation());
        //联系人总结
        contacts.setContactSummary(clue.getContactSummary());
        //创建者
        contacts.setCreateBy(userId);
        //创建时间
        contacts.setCreateTime(DateUtils.nowDateTimeToString());
        //客户id
        contacts.setCustomerId(customer.getId());
        //描述
        contacts.setDescription(clue.getDescription());
        //联系人邮箱
        contacts.setEmail(clue.getEmail());
        //联系人名字
        contacts.setFullName(clue.getFullName());
        //联系人工作
        contacts.setJob(clue.getJob());
        //联系人手机号码
        contacts.setMphone(clue.getMphone());
        //联系人所有者
        contacts.setOwner(clue.getOwner());
        //联系人来源
        contacts.setSource(clue.getSource());
        //插!
        contactsMapper.insert(contacts);
        //再把联系人和市场关联也插了
           contactsMapper.insertContactsActivityRelation(contacts.getId(),activityId);



           //封装交易数据添加交易信息
        Tran tran=new Tran();
        tran.setActivityId(activityId);
        tran.setContactsId(contacts.getId());
        tran.setCreateBy(userId);
        tran.setCreateTime(DateUtils.nowDateTimeToString());
        tran.setOwner(userId);
        tran.setCustomerId(customer.getId());
        tran.setDescription("第一次交易");
        tran.setExpectedDate(expectedDate);
        tran.setMoney(money);
        tran.setOrderNo(UUID.randomUUID().toString());
        tran.setSource(clue.getSource());
        tran.setType(46);
        tran.setName(name);
        //交易数据插入
        tranMapper.insertSelective(tran);

        //封装交易历史阶段信息添加到表中
        TranHistory tranHistory=new TranHistory();
        tranHistory.setCreateBy(userId);
        tranHistory.setCreateTime(DateUtils.nowDateTimeToString());
        tranHistory.setExpectedDate(expectedDate);
        tranHistory.setMoney(money);
        tranHistory.setOrderNo(1);//排序号
        tranHistory.setStage(stage);
        tranHistory.setTranId(tran.getId());
        //4.4 添加交易历史记录
        tranHistoryMapper.insert(tranHistory);
        return 0;
    }
}
