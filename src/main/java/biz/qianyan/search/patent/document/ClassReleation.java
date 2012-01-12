package biz.qianyan.search.patent.document;

public class ClassReleation {
    private Integer id;
    private String  classkey;
    private String  patentcode;
    private String  mainclass;
    private String  subclass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClasskey() {
        return classkey;
    }

    public void setClasskey(String classkey) {
        this.classkey = classkey;
    }

    public String getPatentcode() {
        return patentcode;
    }

    public void setPatentcode(String patentcode) {
        this.patentcode = patentcode;
    }

    public String getMainclass() {
        return mainclass;
    }

    public void setMainclass(String mainclass) {
        this.mainclass = mainclass;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

}
