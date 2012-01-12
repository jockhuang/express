package biz.qianyan.search.express.document;

public class ClassResult implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 371891863009421958L;
    private String            classname;
    private int               num;
    private String            shortname;

    public ClassResult(String classname, int num) {
        super();
        this.classname = classname;
        cutclass();
        this.num = num;
    }

    private void cutclass() {
        String[] s = classname.split(">");
        int i = s.length - 1;
        String c = s[i];
        if ("其他".equals(c)) {
            c = s[i - 1];
        }
        shortname = c;
    }

    /**
     * @return the classname
     */
    public String getClassname() {
        return classname;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @return the shortname
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * @param shortname
     *            the shortname to set
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * @param classname
     *            the classname to set
     */
    public void setClassname(String classname) {
        this.classname = classname;
        cutclass();
    }

    /**
     * @param num
     *            the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("classname=" + classname + "\n");
        sb.append("num=" + num + "\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "首页>机械及工业制品>通用机械设备>其他";
        String[] s = str.split(">");
        int i = s.length - 1;
        String c = s[i];
        if ("其他".equals(c)) {
            c = s[i - 1];
        }
        System.out.print(c);
    }
}
