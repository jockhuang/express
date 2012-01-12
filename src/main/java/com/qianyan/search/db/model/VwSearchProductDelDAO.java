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
public class VwSearchProductDelDAO extends SqlMapClientDaoSupport  implements DeleteItemDAO{
    public List<DeleteItem> findAll(long id){
        return null;
    }
}
