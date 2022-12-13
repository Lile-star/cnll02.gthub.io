package com.crm.workbench.customer.mapper;

import com.crm.workbench.customer.model.ContactsRemark;

public interface ContactsRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContactsRemark record);

    int insertSelective(ContactsRemark record);

    ContactsRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContactsRemark record);

    int updateByPrimaryKey(ContactsRemark record);
}