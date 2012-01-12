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
public class VwSearchPatentDAO  extends SqlMapClientDaoSupport{
    public List<VwSearchPatent> findAll(int id){
        return null;
    }
    
    public int count(){
        return 0;
    }
}
