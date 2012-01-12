/**
 * 
 */
package com.qianyan.search.zhuanli.document;

/**
 * @author Jock
 */
public class PatentDocument {

    /** id */
    private int    id;
    /** url */
    private String url;
    /** 申请号 */
    private String applyno;
    /** 发明名称 */
    private String title;
    /** title */
    private String colortitle;
    /** 摘要 */
    private String brief;
    /** 权利要求 */
    private String rightrequest;
    /** 国际分类号 */
    private String internationalno;
    /** 范畴分类号 */
    private String categoryno;
    /** 申请人 */
    private String proposer;
    /** 国家/省市 */
    private String country;
    /** 联系地址 */
    private String address;
    /** 邮编 */
    private String zip;
    /** 代理人 */
    private String agent;
    /** 代理机构 */
    private String agentorg;
    /** 代理机构地址 */
    private String agentorgaddress;
    /** 发明人 */
    private String originator;
    /** 申请日 */
    private String applydate;
    /** 公开号 */
    private String publicno;
    /** 公开日 */
    private String publicdate;
    /** 授权公告日 */
    private String commisionbulletindate;
    /** 公告日 */
    private String bulletindate;
    /** 授权日 */
    private String commisiondate;
    /** 公告号 */
    private String bulletinno;
    /** 优先权 */
    private String priority;
    /** 审批历史 */
    private String approvehistory;
    /** 附图数 */
    private String attachedpicno;
    /** 页数 */
    private String pagenum;
    /** 权利要求项数 */
    private String askrightnum;

    /** 国际公布号 */
    private String interpublicno;
    /** 国际公布日 */
    private String interpublicdate;
    /** 国际公布语言 */
    private String interpubliclang;
    /** 国际申请号 */
    private String interapplyno;
    /** 国际申请日 */
    private String interapplydate;
    /** 进入国家阶段日 */
    private String internationalizationdate;
    /** PCT */
    private String pct;

    /** 专利分类 发明1,实用新型2,外观3, 国外发明8,国外实用新型9 */
    private String classes;

    /**
     * @return the colortitle
     */
    public String getColortitle() {
        return colortitle;
    }

    /**
     * @param colortitle the colortitle to set
     */
    public void setColortitle(String colortitle) {
        this.colortitle = colortitle;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getUrl() {
        return url;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the applyno
     */
    public String getApplyno() {
        return applyno;
    }

    /**
     * @param applyno
     *            the applyno to set
     */
    public void setApplyno(String applyno) {
        this.applyno = applyno;
        this.url = "http://www.qianyan.biz/Patent-Display/" + applyno + ".html";
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the brief
     */
    public String getBrief() {
        return brief;
    }

    /**
     * @param brief
     *            the brief to set
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * @return the rightrequest
     */
    public String getRightrequest() {
        return rightrequest;
    }

    /**
     * @param rightrequest
     *            the rightrequest to set
     */
    public void setRightrequest(String rightrequest) {
        this.rightrequest = rightrequest;
    }

    /**
     * @return the internationalno
     */
    public String getInternationalno() {
        return internationalno;
    }

    /**
     * @param internationalno
     *            the internationalno to set
     */
    public void setInternationalno(String internationalno) {
        this.internationalno = internationalno;
    }

    /**
     * @return the categoryno
     */
    public String getCategoryno() {
        return categoryno;
    }

    /**
     * @param categoryno
     *            the categoryno to set
     */
    public void setCategoryno(String categoryno) {
        this.categoryno = categoryno;
    }

    /**
     * @return the proposer
     */
    public String getProposer() {
        return proposer;
    }

    /**
     * @param proposer
     *            the proposer to set
     */
    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     *            the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the agent
     */
    public String getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * @return the agentorg
     */
    public String getAgentorg() {
        return agentorg;
    }

    /**
     * @param agentorg
     *            the agentorg to set
     */
    public void setAgentorg(String agentorg) {
        this.agentorg = agentorg;
    }

    /**
     * @return the agentorgaddress
     */
    public String getAgentorgaddress() {
        return agentorgaddress;
    }

    /**
     * @param agentorgaddress
     *            the agentorgaddress to set
     */
    public void setAgentorgaddress(String agentorgaddress) {
        this.agentorgaddress = agentorgaddress;
    }

    /**
     * @return the originator
     */
    public String getOriginator() {
        return originator;
    }

    /**
     * @param originator
     *            the originator to set
     */
    public void setOriginator(String originator) {
        this.originator = originator;
    }

    /**
     * @return the applydate
     */
    public String getApplydate() {
        return applydate;
    }

    /**
     * @param applydate
     *            the applydate to set
     */
    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    /**
     * @return the publicno
     */
    public String getPublicno() {
        return publicno;
    }

    /**
     * @param publicno
     *            the publicno to set
     */
    public void setPublicno(String publicno) {
        this.publicno = publicno;
    }

    /**
     * @return the publicdate
     */
    public String getPublicdate() {
        return publicdate;
    }

    /**
     * @param publicdate
     *            the publicdate to set
     */
    public void setPublicdate(String publicdate) {
        this.publicdate = publicdate;
    }

    /**
     * @return the commisionbulletindate
     */
    public String getCommisionbulletindate() {
        return commisionbulletindate;
    }

    /**
     * @param commisionbulletindate
     *            the commisionbulletindate to set
     */
    public void setCommisionbulletindate(String commisionbulletindate) {
        this.commisionbulletindate = commisionbulletindate;
    }

    /**
     * @return the bulletindate
     */
    public String getBulletindate() {
        return bulletindate;
    }

    /**
     * @param bulletindate
     *            the bulletindate to set
     */
    public void setBulletindate(String bulletindate) {
        this.bulletindate = bulletindate;
    }

    /**
     * @return the commisiondate
     */
    public String getCommisiondate() {
        return commisiondate;
    }

    /**
     * @param commisiondate
     *            the commisiondate to set
     */
    public void setCommisiondate(String commisiondate) {
        this.commisiondate = commisiondate;
    }

    /**
     * @return the bulletinno
     */
    public String getBulletinno() {
        return bulletinno;
    }

    /**
     * @param bulletinno
     *            the bulletinno to set
     */
    public void setBulletinno(String bulletinno) {
        this.bulletinno = bulletinno;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the approvehistory
     */
    public String getApprovehistory() {
        return approvehistory;
    }

    /**
     * @param approvehistory
     *            the approvehistory to set
     */
    public void setApprovehistory(String approvehistory) {
        this.approvehistory = approvehistory;
    }

    /**
     * @return the attachedpicno
     */
    public String getAttachedpicno() {
        return attachedpicno;
    }

    /**
     * @param attachedpicno
     *            the attachedpicno to set
     */
    public void setAttachedpicno(String attachedpicno) {
        this.attachedpicno = attachedpicno;
    }

    /**
     * @return the pagenum
     */
    public String getPagenum() {
        return pagenum;
    }

    /**
     * @param pagenum
     *            the pagenum to set
     */
    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    /**
     * @return the askrightnum
     */
    public String getAskrightnum() {
        return askrightnum;
    }

    /**
     * @param askrightnum
     *            the askrightnum to set
     */
    public void setAskrightnum(String askrightnum) {
        this.askrightnum = askrightnum;
    }

    /**
     * @return the interpublicno
     */
    public String getInterpublicno() {
        return interpublicno;
    }

    /**
     * @param interpublicno
     *            the interpublicno to set
     */
    public void setInterpublicno(String interpublicno) {
        this.interpublicno = interpublicno;
    }

    /**
     * @return the interpublicdate
     */
    public String getInterpublicdate() {
        return interpublicdate;
    }

    /**
     * @param interpublicdate
     *            the interpublicdate to set
     */
    public void setInterpublicdate(String interpublicdate) {
        this.interpublicdate = interpublicdate;
    }

    /**
     * @return the interpubliclang
     */
    public String getInterpubliclang() {
        return interpubliclang;
    }

    /**
     * @param interpubliclang
     *            the interpubliclang to set
     */
    public void setInterpubliclang(String interpubliclang) {
        this.interpubliclang = interpubliclang;
    }

    /**
     * @return the interapplyno
     */
    public String getInterapplyno() {
        return interapplyno;
    }

    /**
     * @param interapplyno
     *            the interapplyno to set
     */
    public void setInterapplyno(String interapplyno) {
        this.interapplyno = interapplyno;
    }

    /**
     * @return the interapplydate
     */
    public String getInterapplydate() {
        return interapplydate;
    }

    /**
     * @param interapplydate
     *            the interapplydate to set
     */
    public void setInterapplydate(String interapplydate) {
        this.interapplydate = interapplydate;
    }

    /**
     * @return the internationalizationdate
     */
    public String getInternationalizationdate() {
        return internationalizationdate;
    }

    /**
     * @param internationalizationdate
     *            the internationalizationdate to set
     */
    public void setInternationalizationdate(String internationalizationdate) {
        this.internationalizationdate = internationalizationdate;
    }

    /**
     * @return the pct
     */
    public String getPct() {
        return pct;
    }

    /**
     * @param pct
     *            the pct to set
     */
    public void setPct(String pct) {
        this.pct = pct;
    }

}
