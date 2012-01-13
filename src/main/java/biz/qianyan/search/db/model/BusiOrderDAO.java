package biz.qianyan.search.db.model;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BusiOrderDAO extends SqlMapClientDaoSupport {

    public List<BusiOrder> findAll() {
        return getSqlMapClientTemplate().queryForList("order.getOrderList");
    }
}
