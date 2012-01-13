package biz.qianyan.search.db.model;

import java.util.Date;

/**
 * VwSearchProductDel entity. @author MyEclipse Persistence Tools
 */

public class VwSearchProductDel implements DeleteItem, java.io.Serializable {

    // Fields

    private Integer id;
    private Date deldate;
    private Long idid;

    // Constructors

    /** default constructor */
    public VwSearchProductDel() {
    }

    /** minimal constructor */
    public VwSearchProductDel(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public VwSearchProductDel(Integer id, Date deldate, Long idid) {
        this.id = id;
        this.deldate = deldate;
        this.idid = idid;
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
        return this.idid;
    }

    public void setIdid(Long idid) {
        this.idid = idid;
    }

}
