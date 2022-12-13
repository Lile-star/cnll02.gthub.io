package com.crm.workbench.customer.mapper;

import com.crm.workbench.customer.model.Contacts;

public interface ContactsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);

    void insertContactsActivityRelation(Integer id, Integer integer);
}