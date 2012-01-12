package biz.qianyan.search.db.model;

/**
 * PatentClassMain entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatentClassMain implements java.io.Serializable {

    // Fields

    private Integer id;
    private String  classkey;
    private String  patentcode;
    private String  mainclass;
    private String  subclass;

    // Constructors

    /** default constructor */
    public PatentClassMain() {
    }

    /** full constructor */
    public PatentClassMain(String classkey, String patentcode, String mainclass, String subclass) {
        this.classkey = classkey;
        this.patentcode = patentcode;
        this.mainclass = mainclass;
        this.subclass = subclass;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClasskey() {
        return this.classkey;
    }

    public void setClasskey(String classkey) {
        this.classkey = classkey;
    }

    public String getPatentcode() {
        return this.patentcode;
    }

    public void setPatentcode(String patentcode) {
        this.patentcode = patentcode;
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

}