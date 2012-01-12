package biz.qianyan.search.db.model;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class VwSearchCompanyDelDAO  extends SqlMapClientDaoSupport implements DeleteItemDAO{
    public List<DeleteItem> findAll(long id){
        return null;
    }
}
