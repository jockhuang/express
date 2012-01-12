/**
 * 
 */
package biz.qianyan.search.express.document;

import java.util.Date;

/**
 * @author Jock
 */
public class AdDocument {

    private Integer id;
    private String  keyword;
    private String  title;
    private String  brief;
    private String  picurl;
    private String  url;
    private String  itemname;
    private String  itemurl;
    private Integer ssid;
    private Short   rank;
    private Date    begindate;
    private Date    enddate;
    private Short   type;
    private Short   searchtype;
    private Short   infotype;
    private Integer payid;
    private Integer agentid;
    private Integer userid;
    private Integer yid;
    private String  memo;
    private Short   status;
    private Date    createdate;
    private Integer comid;
    private String  messageurl;
    private String  fullPath;
    private String  regionname;
    private Short   configtype;
    private Short   adtype;

    private String  comurl;

    /**
     * @return the comurl
     */
    public String getComurl() {
        return comurl;
    }

    /**
     * @param comurl
     *            the comurl to set
     */
    public void setComurl(String comurl) {
        this.comurl = comurl;
    }

    /**
     * @return the itemname
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * @param itemname
     *            the itemname to set
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * @return the adtype
     */
    public Short getAdtype() {
        return adtype;
    }

    /**
     * @param adtype
     *            the adtype to set
     */
    public void setAdtype(Short adtype) {
        this.adtype = adtype;
    }

    /**
     * @return the configtype
     */
    public Short getConfigtype() {
        return configtype;
    }

    /**
     * @param configtype
     *            the configtype to set
     */
    public void setConfigtype(Short configtype) {
        this.configtype = configtype;
    }

    /**
     * @return the fullPath
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * @param fullPath
     *            the fullPath to set
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     * @return the regionname
     */
    public String getRegionname() {
        return regionname;
    }

    /**
     * @param regionname
     *            the regionname to set
     */
    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    /**
     * @return the messageurl
     */
    public String getMessageurl() {
        return messageurl;
    }

    /**
     * @return the searchtype
     */
    public Short getSearchtype() {
        return searchtype;
    }

    /**
     * @param searchtype
     *            the searchtype to set
     */
    public void setSearchtype(Short searchtype) {
        this.searchtype = searchtype;
    }

    /**
     * @return the infotype
     */
    public Short getInfotype() {
        return infotype;
    }

    /**
     * @param infotype
     *            the infotype to set
     */
    public void setInfotype(Short infotype) {
        this.infotype = infotype;
    }

    /**
     * @param messageurl
     *            the messageurl to set
     */
    public void setMessageurl(String messageurl) {
        this.messageurl = messageurl;
    }

    /**
     * @return the comid
     */
    public Integer getComid() {
        return comid;
    }

    /**
     * @param comid
     *            the comid to set
     */
    public void setComid(Integer comid) {
        this.comid = comid;
    }

    /**
     * @return the id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the keyword
     */
    public final String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     *            the keyword to set
     */
    public final void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public final void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the brief
     */
    public final String getBrief() {
        return brief;
    }

    /**
     * @param brief
     *            the brief to set
     */
    public final void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * @return the picurl
     */
    public final String getPicurl() {
        return picurl;
    }

    /**
     * @param picurl
     *            the picurl to set
     */
    public final void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * @return the url
     */
    public final String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public final void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the itemurl
     */
    public final String getItemurl() {
        return itemurl;
    }

    /**
     * @param itemurl
     *            the itemurl to set
     */
    public final void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    /**
     * @return the ssid
     */
    public final Integer getSsid() {
        return ssid;
    }

    /**
     * @param ssid
     *            the ssid to set
     */
    public final void setSsid(Integer ssid) {
        this.ssid = ssid;
        if (ssid != null && ssid > 0) this.url = "http://www.qianyan.biz/sshow-" + ssid + ".html";
    }

    /**
     * @return the rank
     */
    public final Short getRank() {
        return rank;
    }

    /**
     * @param rank
     *            the rank to set
     */
    public final void setRank(Short rank) {
        this.rank = rank;
    }

    /**
     * @return the begindate
     */
    public final Date getBegindate() {
        return begindate;
    }

    /**
     * @param begindate
     *            the begindate to set
     */
    public final void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    /**
     * @return the enddate
     */
    public final Date getEnddate() {
        return enddate;
    }

    /**
     * @param enddate
     *            the enddate to set
     */
    public final void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * @return the type
     */
    public final Short getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public final void setType(Short type) {
        this.type = type;
    }

    /**
     * @return the payid
     */
    public final Integer getPayid() {
        return payid;
    }

    /**
     * @param payid
     *            the payid to set
     */
    public final void setPayid(Integer payid) {
        this.payid = payid;
    }

    /**
     * @return the agentid
     */
    public final Integer getAgentid() {
        return agentid;
    }

    /**
     * @param agentid
     *            the agentid to set
     */
    public final void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    /**
     * @return the userid
     */
    public final Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     *            the userid to set
     */
    public final void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return the yid
     */
    public final Integer getYid() {
        return yid;
    }

    /**
     * @param yid
     *            the yid to set
     */
    public final void setYid(Integer yid) {
        this.yid = yid;
    }

    /**
     * @return the memo
     */
    public final String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public final void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the status
     */
    public final Short getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public final void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return the createdate
     */
    public final Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     *            the createdate to set
     */
    public final void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id=" + id + "\n");

        sb.append("brief=" + brief + "\n");
        sb.append("itemname=" + itemname + "\n");
        sb.append("comurl=" + comurl + "\n");
        sb.append("createdate=" + createdate + "\n");
        sb.append("fullPath=" + fullPath + "\n");
        sb.append("id=" + id + "\n");
        sb.append("infotype=" + infotype + "\n");
        sb.append("picurl=" + picurl + "\n");
        sb.append("regionname=" + regionname + "\n");
        sb.append("title=" + title + "\n");
        sb.append("type=" + type + "\n");
        sb.append("url=" + url + "\n");
        sb.append("comid=" + comid + "\n");
        sb.append("searchtype=" + searchtype + "\n");
        sb.append("infotype=" + infotype + "\n");
        sb.append("adtype=" + adtype + "\n");

        return sb.toString();
    }
}
