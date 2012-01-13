package biz.qianyan.search.db.model;

import java.util.Date;

/**
 * VwSearchCompanyDel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwSearchCompanyDel implements DeleteItem, java.io.Serializable {

    // Fields

    private Integer id;
    private Date deldate;

    // Constructors

    /** default constructor */
    public VwSearchCompanyDel() {
    }

    /** full constructor */
    public VwSearchCompanyDel(Date deldate) {
        this.deldate = deldate;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDeldate() {
        return this.deldate;
    }

    public void setDeldate(Date deldate) {
        this.deldate = deldate;
    }

    public Long getIdid() {
        return this.deldate.getTime();
    }

    public void setIdid(Long idid) {

    }

}
