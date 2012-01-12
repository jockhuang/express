package biz.qianyan.search.db.model;

import java.util.Date;

/**
 * BusiOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BusiOrder implements java.io.Serializable {

    // Fields

    private Integer id;
    private String  keyword;
    private String  title;
    private String  brief;
    private String  picurl;
    private String  itemurl;
    private String  companyname;
    private String  comurl;
    private String  province;
    private String  classname;
    private Integer ssid;
    private Short   rank;
    private Date    begindate;
    private Date    enddate;
    private Short   type;
    private Short   searchtype;
    private Short   infotype;
    private Short   configtype;
    private Integer payid;
    private Integer agentid;
    private Integer userid;
    private Integer yid;
    private String  memo;
    private Short   status;
    private Date    createdate;

    // Constructors

    /** default constructor */
    public BusiOrder() {
    }

    /** minimal constructor */
    public BusiOrder(String keyword, String title, String brief, Integer ssid, Short rank,
                     Date begindate, Date enddate, Short type, Short infotype, Integer payid,
                     Integer agentid, Integer userid, Integer yid, String memo, Short status,
                     Date createdate) {
        this.keyword = keyword;
        this.title = title;
        this.brief = brief;
        this.ssid = ssid;
        this.rank = rank;
        this.begindate = begindate;
        this.enddate = enddate;
        this.type = type;
        this.infotype = infotype;
        this.payid = payid;
        this.agentid = agentid;
        this.userid = userid;
        this.yid = yid;
        this.memo = memo;
        this.status = status;
        this.createdate = createdate;
    }

    /** full constructor */
    public BusiOrder(String keyword, String title, String brief, String picurl, String itemurl,
                     String companyname, String comurl, String province, String classname,
                     Integer ssid, Short rank, Date begindate, Date enddate, Short type,
                     Short searchtype, Short infotype, Short configtype, Integer payid,
                     Integer agentid, Integer userid, Integer yid, String memo, Short status,
                     Date createdate) {
        this.keyword = keyword;
        this.title = title;
        this.brief = brief;
        this.picurl = picurl;
        this.itemurl = itemurl;
        this.companyname = companyname;
        this.comurl = comurl;
        this.province = province;
        this.classname = classname;
        this.ssid = ssid;
        this.rank = rank;
        this.begindate = begindate;
        this.enddate = enddate;
        this.type = type;
        this.searchtype = searchtype;
        this.infotype = infotype;
        this.configtype = configtype;
        this.payid = payid;
        this.agentid = agentid;
        this.userid = userid;
        this.yid = yid;
        this.memo = memo;
        this.status = status;
        this.createdate = createdate;
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

    public Date getBegindate() {
        return this.begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return this.enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getSearchtype() {
        return this.searchtype;
    }

    public void setSearchtype(Short searchtype) {
        this.searchtype = searchtype;
    }

    public Short getInfotype() {
        return this.infotype;
    }

    public void setInfotype(Short infotype) {
        this.infotype = infotype;
    }

    public Short getConfigtype() {
        return this.configtype;
    }

    public void setConfigtype(Short configtype) {
        this.configtype = configtype;
    }

    public Integer getPayid() {
        return this.payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public Integer getAgentid() {
        return this.agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getYid() {
        return this.yid;
    }

    public void setYid(Integer yid) {
        this.yid = yid;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}