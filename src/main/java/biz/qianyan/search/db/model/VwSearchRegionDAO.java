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
public class VwSearchRegionDAO extends SqlMapClientDaoSupport {
    public List<VwSearchRegion> findProvince(){
        return null;
    }
    public List<VwSearchRegion> findCity(String key){
        return null;
    }
}
