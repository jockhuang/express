package biz.qianyan.search.db.model;

import java.util.Date;

/**
 * PatentClass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatentClass implements java.io.Serializable {

    // Fields

    private Integer id;
    private String mainclass;
    private String subclass;
    private String classname;
    private Date createdate;

    // Constructors

    /** default constructor */
    public PatentClass() {
    }

    /** full constructor */
    public PatentClass(String mainclass, String subclass, String classname, Date createdate) {
        this.mainclass = mainclass;
        this.subclass = subclass;
        this.classname = classname;
        this.createdate = createdate;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainclass() {
        return this.mainclass;
    }

    public void setMainclass(String mainclass) {
        this.mainclass = mainclass;
    }

    public String getSubclass() {
        return this.subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}
