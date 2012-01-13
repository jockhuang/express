/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package biz.qianyan.search.express.web.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 07-14-2008 XDoclet definition:
 * 
 * @struts.form name="releateForm"
 */
public class ReleateForm extends ActionForm {
    /*
     * Generated fields
     */

    /** fts property */
    private Integer fts;

    /** w property */
    private Integer w;

    /** ln property */
    private Integer ln;

    /** t property */
    private String t;

    /** ftc property */
    private String ftc;

    /** bc property */
    private String bc;

    /** fc property */
    private String fc;

    private int s;

    private String img;

    private int th;

    private int u;

    /*
     * Generated Methods
     */

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Method validate
     * 
     * @param mapping
     * @param request
     * @return ActionErrors
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        return null;
    }

    /**
     * Method reset
     * 
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        fc = "000000";
        bc = "FFFFFF";
        ftc = "000000";
        fts = 14;
        ln = 10;
        w = 200;
        s = 1;
        th = 22;
        img = "con2.gif";
        u = 1;
    }

    /**
     * @return the th
     */
    public int getTh() {
        return th;
    }

    /**
     * @param th the th to set
     */
    public void setTh(int th) {
        this.th = th;
    }

    /**
     * Returns the fts.
     * 
     * @return Integer
     */
    public Integer getFts() {
        return fts;
    }

    /**
     * @return the s
     */
    public int getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(int s) {
        this.s = s;
    }

    /**
     * Set the fts.
     * 
     * @param fts The fts to set
     */
    public void setFts(Integer fts) {
        this.fts = fts;
    }

    /**
     * Returns the w.
     * 
     * @return Integer
     */
    public Integer getW() {
        return w;
    }

    /**
     * Set the w.
     * 
     * @param w The w to set
     */
    public void setW(Integer w) {
        this.w = w;
    }

    /**
     * Returns the ln.
     * 
     * @return Integer
     */
    public Integer getLn() {
        return ln;
    }

    /**
     * Set the ln.
     * 
     * @param ln The ln to set
     */
    public void setLn(Integer ln) {
        this.ln = ln;
    }

    /**
     * Returns the t.
     * 
     * @return String
     */
    public String getT() {
        return t;
    }

    /**
     * Set the t.
     * 
     * @param t The t to set
     */
    public void setT(String t) {
        this.t = t;
    }

    /**
     * Returns the ftc.
     * 
     * @return String
     */
    public String getFtc() {
        return ftc;
    }

    /**
     * Set the ftc.
     * 
     * @param ftc The ftc to set
     */
    public void setFtc(String ftc) {
        this.ftc = ftc;
    }

    /**
     * Returns the bc.
     * 
     * @return String
     */
    public String getBc() {
        return bc;
    }

    /**
     * Set the bc.
     * 
     * @param bc The bc to set
     */
    public void setBc(String bc) {
        this.bc = bc;
    }

    /**
     * Returns the fc.
     * 
     * @return String
     */
    public String getFc() {
        return fc;
    }

    /**
     * Set the fc.
     * 
     * @param fc The fc to set
     */
    public void setFc(String fc) {
        this.fc = fc;
    }

    /**
     * @return the u
     */
    public int getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(int u) {
        this.u = u;
    }
}
