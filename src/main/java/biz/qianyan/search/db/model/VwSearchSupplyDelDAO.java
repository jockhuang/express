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
public class VwSearchSupplyDelDAO  extends SqlMapClientDaoSupport implements DeleteItemDAO{
    public List<DeleteItem> findAll(long id){
        return null;
    }
}
