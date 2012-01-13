/**
 * Specl.com Inc.
 * Copyright (c) 2010-2012 All Rights Reserved.
 */
package biz.qianyan.search.db.model;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 
 * @author Jock
 */
public class VwSearchSupplyDAO extends SqlMapClientDaoSupport {
    public List<VwSearchSupply> findAll(int id){
        return getSqlMapClientTemplate().queryForList("supply.getSupplyList",id);
    }
    public List<VwSearchSupply> findAllLt(int id){
        return getSqlMapClientTemplate().queryForList("supply.getSupplyList",id);
    }
    public List<VwSearchSupply> findBySsid(int ssid){
        return getSqlMapClientTemplate().queryForList("supply.getSupplyListBySsid",ssid);
    }
    
}
