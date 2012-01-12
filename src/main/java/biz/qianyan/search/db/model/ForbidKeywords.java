package biz.qianyan.search.db.model;

/**
 * ForbidKeywords entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ForbidKeywords implements java.io.Serializable {

    // Fields

    private String keyword;

    // Constructors

    /** default constructor */
    public ForbidKeywords() {
    }

    // Property accessors

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}