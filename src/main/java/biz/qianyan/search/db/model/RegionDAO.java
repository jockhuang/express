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
public class RegionDAO extends SqlMapClientDaoSupport {
    public List<Region> findProvince() {
        return getSqlMapClientTemplate().queryForList("region.getProvinceList");
    }

    public List<Region> findCity(String key) {
        return getSqlMapClientTemplate().queryForList("region.getCityList", key);
    }
}
