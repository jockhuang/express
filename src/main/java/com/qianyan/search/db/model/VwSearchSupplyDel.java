package com.qianyan.search.db.model;

/**
 * VwSearchSupplyDel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwSearchSupplyDel implements DeleteItem , java.io.Serializable {

    // Fields

    private Integer id;
    private Long idid;

    // Constructors

    /** default constructor */
    public VwSearchSupplyDel() {
    }

    /** full constructor */
    public VwSearchSupplyDel(Long idid) {
        this.idid = idid;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdid() {
        return this.idid;
    }

    public void setIdid(Long idid) {
        this.idid = idid;
    }
    
    public void setIdid(Integer idid) {
        this.idid = idid.longValue();
    }

}