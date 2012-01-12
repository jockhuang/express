/**
 * Specl.com Inc.
 * Copyright (c) 2010-2012 All Rights Reserved.
 */
package com.qianyan.search.db.model;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 
 * @author Jock
 */
public class VwSearchComsDAO  extends SqlMapClientDaoSupport{
    public List<VwSearchComs> findAll(int id){
        return getSqlMapClientTemplate().queryForList("company.getCompanyList",id);
    }
    public List<VwSearchComs> findAll2(int id){
        return getSqlMapClientTemplate().queryForList("company.getCompanyList",id);
    }
    public int count(){
        return (Integer)getSqlMapClientTemplate().queryForObject("");
    }
    public VwSearchComs findById(int id){
        return (VwSearchComs)getSqlMapClientTemplate().queryForObject("",id);
    }
}
