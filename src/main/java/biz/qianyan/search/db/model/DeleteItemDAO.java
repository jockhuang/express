/**
 * Specl.com Inc.
 * Copyright (c) 2010-2012 All Rights Reserved.
 */
package biz.qianyan.search.db.model;

import java.util.List;

/**
 * 
 * @author Jock
 */
public interface DeleteItemDAO {
    public List<DeleteItem> findAll(long id);

}
