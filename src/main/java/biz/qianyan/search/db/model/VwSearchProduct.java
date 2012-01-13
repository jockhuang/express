package biz.qianyan.search.db.model;

import java.util.Date;

/**
 * VwSearchProduct entity. @author MyEclipse Persistence Tools
 */

public class VwSearchProduct implements java.io.Serializable {

    // Fields

    private Integer id;
    private String url;
    private String title;
    private String classkey;
    private String regionkey;
    private Short infotype;
    private Date createdate;
    private String brief;
    private String content;
    private String keywords;
    private String itemname;
    private String picurl;
    private String comurl;
    private Short credit;
    private String regionname;
    private String contact;
    private String address;
    private String zipcode;
    private String phonecountry;
    private String phonearea;
    private String tel;
    private String faxcountry;
    private String faxarea;
    private String fax;
    private String email;
    private String route;
    private String mobile;
    private String price;
    private String quantity;
    private String qqcode;
    private String msncode;
    private String fullPath;
    private Integer comid;
    private Short mainmode;
    private Short maintrade;
    private Integer ssid;

    // Constructors

    /** default constructor */
    public VwSearchProduct() {
    }

    /** minimal constructor */
    public VwSearchProduct(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public VwSearchProduct(Integer id, String url, String title, String classKey, String regionkey, Short infotype,
            Date createdate, String brief, String content, String keywords, String itemname, String picurl,
            String comurl, Short credit, String regionname, String contact, String address, String zipcode,
            String phonecountry, String phonearea, String tel, String faxcountry, String faxarea, String fax,
            String email, String route, String mobile, String price, String quantity, String qqcode, String msncode,
            String fullPath, Integer comid, Short mainmode, Short maintrade, Integer ssid) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.classkey = classKey;
        this.regionkey = regionkey;
        this.infotype = infotype;
        this.createdate = createdate;
        this.brief = brief;
        this.content = content;
        this.keywords = keywords;
        this.itemname = itemname;
        this.picurl = picurl;
        this.comurl = comurl;
        this.credit = credit;
        this.regionname = regionname;
        this.contact = contact;
        this.address = address;
        this.zipcode = zipcode;
        this.phonecountry = phonecountry;
        this.phonearea = phonearea;
        this.tel = tel;
        this.faxcountry = faxcountry;
        this.faxarea = faxarea;
        this.fax = fax;
        this.email = email;
        this.route = route;
        this.mobile = mobile;
        this.price = price;
        this.quantity = quantity;
        this.qqcode = qqcode;
        this.msncode = msncode;
        this.fullPath = fullPath;
        this.comid = comid;
        this.mainmode = mainmode;
        this.maintrade = maintrade;
        this.ssid = ssid;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClasskey() {
        return this.classkey;
    }

    public void setClasskey(String classKey) {
        this.classkey = classKey;
    }

    public String getRegionkey() {
        return this.regionkey;
    }

    public void setRegionkey(String regionkey) {
        this.regionkey = regionkey;
    }

    public Short getInfotype() {
        return this.infotype;
    }

    public void setInfotype(Short infotype) {
        this.infotype = infotype;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getBrief() {
        return this.brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getItemname() {
        return this.itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPicurl() {
        return this.picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getComurl() {
        return this.comurl;
    }

    public void setComurl(String comurl) {
        this.comurl = comurl;
    }

    public Short getCredit() {
        return this.credit;
    }

    public void setCredit(Short credit) {
        this.credit = credit;
    }

    public String getRegionname() {
        return this.regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhonecountry() {
        return this.phonecountry;
    }

    public void setPhonecountry(String phonecountry) {
        this.phonecountry = phonecountry;
    }

    public String getPhonearea() {
        return this.phonearea;
    }

    public void setPhonearea(String phonearea) {
        this.phonearea = phonearea;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFaxcountry() {
        return this.faxcountry;
    }

    public void setFaxcountry(String faxcountry) {
        this.faxcountry = faxcountry;
    }

    public String getFaxarea() {
        return this.faxarea;
    }

    public void setFaxarea(String faxarea) {
        this.faxarea = faxarea;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQqcode() {
        return this.qqcode;
    }

    public void setQqcode(String qqcode) {
        this.qqcode = qqcode;
    }

    public String getMsncode() {
        return this.msncode;
    }

    public void setMsncode(String msncode) {
        this.msncode = msncode;
    }

    public String getFullPath() {
        return this.fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Integer getComid() {
        return this.comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public Short getMainmode() {
        return this.mainmode;
    }

    public void setMainmode(Short mainmode) {
        this.mainmode = mainmode;
    }

    public Short getMaintrade() {
        return this.maintrade;
    }

    public void setMaintrade(Short maintrade) {
        this.maintrade = maintrade;
    }

    public Integer getSsid() {
        return this.ssid;
    }

    public void setSsid(Integer ssid) {
        this.ssid = ssid;
    }

}
