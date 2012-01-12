package com.qianyan.search.db.model;

/**
 * VwSearchRegion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwSearchRegion implements java.io.Serializable {

    // Fields

    private String classkey;
    private String classname;
    private String fullpath;
    private String pointto;

    // Constructors

    /** default constructor */
    public VwSearchRegion() {
    }

    /** minimal constructor */
    public VwSearchRegion(String classkey) {
        this.classkey = classkey;
    }

    /** full constructor */
    public VwSearchRegion(String classkey, String classname, String fullpath, String pointto) {
        this.classkey = classkey;
        this.classname = classname;
        this.fullpath = fullpath;
        this.pointto = pointto;
    }

    // Property accessors

    public String getClasskey() {
        return this.classkey;
    }

    public void setClasskey(String classkey) {
        this.classkey = classkey;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getFullpath() {
        return this.fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public String getPointto() {
        return this.pointto;
    }

    public void setPointto(String pointto) {
        this.pointto = pointto;
    }

}