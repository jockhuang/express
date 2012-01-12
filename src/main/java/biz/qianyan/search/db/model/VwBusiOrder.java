package biz.qianyan.search.db.model;

/**
 * VwBusiOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VwBusiOrder implements java.io.Serializable {

    // Fields

    private Integer id;
    private String  keyword;
    private String  title;
    private String  brief;
    private String  picurl;
    private String  itemurl;
    private Integer ssid;
    private Short   rank;
    private Short   type;
    private Integer comid;
    private Short   status;
    private String  companyname;
    private String  comurl;
    private String  province;
    private String  classname;

    // Constructors

    /** default constructor */
    public VwBusiOrder() {
    }

    /** minimal constructor */
    public VwBusiOrder(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public VwBusiOrder(Integer id, String keyword, String title, String brief, String picurl,
                       String itemurl, Integer ssid, Short rank, Short type, Integer comid,
                       Short status, String companyname, String comurl, String province,
                       String classname) {
        this.id = id;
        this.keyword = keyword;
        this.title = title;
        this.brief = brief;
        this.picurl = picurl;
        this.itemurl = itemurl;
        this.ssid = ssid;
        this.rank = rank;
        this.type = type;
        this.comid = comid;
        this.status = status;
        this.companyname = companyname;
        this.comurl = comurl;
        this.province = province;
        this.classname = classname;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return this.brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPicurl() {
        return this.picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getItemurl() {
        return this.itemurl;
    }

    public void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    public Integer getSsid() {
        return this.ssid;
    }

    public void setSsid(Integer ssid) {
        this.ssid = ssid;
    }

    public Short getRank() {
        return this.rank;
    }

    public void setRank(Short rank) {
        this.rank = rank;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getComid() {
        return this.comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getCompanyname() {
        return this.companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getComurl() {
        return this.comurl;
    }

    public void setComurl(String comurl) {
        this.comurl = comurl;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

}