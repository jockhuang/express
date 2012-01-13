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
public class VwSearchProductDAO extends SqlMapClientDaoSupport {
    public List<VwSearchProduct> findAll(int id){
        return getSqlMapClientTemplate().queryForList("product.getProductList",id);
    }
    public List<VwSearchProduct> findBySsid(int ssid){
        return getSqlMapClientTemplate().queryForList("product.getProductListBySsid",ssid);
    }
    
}
