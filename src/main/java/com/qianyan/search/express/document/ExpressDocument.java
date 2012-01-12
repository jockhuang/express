/**
 * 商機搜索檢索文檔.
 */
package com.qianyan.search.express.document;

import java.util.Date;

/**
 * @author Jock
 */
public final class ExpressDocument {

    private String  address;
    private String  brief;
    private String  builddate;
    private String  classkey;
    private String  itemname;
    private String  comurl;
    private String  contact;
    private String  content;
    private Date    createdate;
    private Short   credit;
    private String  email;
    private String  fax;
    private String  faxarea;
    private String  faxcountry;
    private String  fullPath;
    private Integer id;
    private Short   infotype;
    private String  keywords;
    private short   mainmode;
    private String  mobile;
    private String  msncode;
    private String  phonearea;
    private String  phonecountry;
    private String  picurl;
    private String  price;
    private String  qqcode;
    private String  quantity;
    private String  regionkey;
    private String  regionname;
    private String  route;
    private String  tel;
    private String  title;
    private String  orititle;
    private int     type;
    private String  url;
    private String  zipcode;
    private int     comid;
    private String  shortpath;
    private String  messageurl;
    private String  itemurl;
    private String  bgcolor;

    /**
     * @return the orititle
     */
    public String getOrititle() {
        return orititle;
    }

    /**
     * @param orititle the orititle to set
     */
    public void setOrititle(String orititle) {
        this.orititle = orititle;
    }

    /**
     * @return the bgcolor
     */
    public String getBgcolor() {
        return bgcolor;
    }

    /**
     * @param bgcolor
     *            the bgcolor to set
     */
    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    /**
     * @return the itemurl
     */
    public String getItemurl() {
        return itemurl;
    }

    /**
     * @param itemurl
     *            the itemurl to set
     */
    public void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    private void cutclass() {
        if (fullPath != null) {
            String[] s = fullPath.split(">");
            int i = s.length - 1;
            String c = s[i];
            if ("其他".equals(c)) {
                c = s[i - 1];
            }
            shortpath = c;
        }
        // shortpath = fullPath.substring(fullPath.lastIndexOf(">") + 1);
    }

    /**
     * @return the address
     */
    public final String getAddress() {
        return address;
    }

    /**
     * @return the comid
     */
    public int getComid() {
        return comid;
    }

    /**
     * @return the messageurl
     */
    public final String getMessageurl() {
        return messageurl;
    }

    /**
     * @param messageurl
     *            the messageurl to set
     */
    public final void setMessageurl(String messageurl) {
        this.messageurl = messageurl;
    }

    /**
     * @param comid
     *            the comid to set
     */
    public void setComid(int comid) {
        this.comid = comid;
    }

    /**
     * @return the brief
     */
    public final String getBrief() {
        return brief;
    }

    /**
     * @return the builddate
     */
    public String getBuilddate() {
        return builddate;
    }

    /**
     * @return the classkey
     */
    public final String getClasskey() {
        return classkey;
    }

    /**
     * @return the comurl
     */
    public final String getComurl() {
        return comurl;
    }

    /**
     * @return the contact
     */
    public final String getContact() {
        return contact;
    }

    /**
     * @return the content
     */
    public final String getContent() {
        return content;
    }

    /**
     * @return the createdate
     */
    public final Date getCreatedate() {
        return createdate;
    }

    /**
     * @return the credit
     */
    public final Short getCredit() {
        return credit;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @return the fax
     */
    public final String getFax() {
        return fax;
    }

    /**
     * @return the faxarea
     */
    public final String getFaxarea() {
        return faxarea;
    }

    /**
     * @return the faxcountry
     */
    public final String getFaxcountry() {
        return faxcountry;
    }

    /**
     * @return the fullPath
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * @return the id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * @return the infotype
     */
    public final Short getInfotype() {
        return infotype;
    }

    /**
     * @return the keywords
     */
    public final String getKeywords() {
        return keywords;
    }

    /**
     * @return the mainmode
     */
    public short getMainmode() {
        return mainmode;
    }

    /**
     * @return the mobile
     */
    public final String getMobile() {
        return mobile;
    }

    /**
     * @return the msncode
     */
    public final String getMsncode() {
        return msncode;
    }

    /**
     * @return the phonearea
     */
    public final String getPhonearea() {
        return phonearea;
    }

    /**
     * @return the phonecountry
     */
    public final String getPhonecountry() {
        return phonecountry;
    }

    /**
     * @return the picurl
     */
    public final String getPicurl() {
        return picurl;
    }

    /**
     * @return the price
     */
    public final String getPrice() {
        return price;
    }

    /**
     * @return the qqcode
     */
    public final String getQqcode() {
        return qqcode;
    }

    /**
     * @return the quantity
     */
    public final String getQuantity() {
        return quantity;
    }

    /**
     * @return the regionkey
     */
    public final String getRegionkey() {
        return regionkey;
    }

    /**
     * @return the regionname
     */
    public final String getRegionname() {
        return regionname;
    }

    /**
     * @return the route
     */
    public final String getRoute() {
        return route;
    }

    /**
     * @return the tel
     */
    public final String getTel() {
        return tel;
    }

    /**
     * @return the title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @return the url
     */
    public final String getUrl() {
        return url;
    }

    /**
     * @return the zipcode
     */
    public final String getZipcode() {
        return zipcode;
    }

    /**
     * @param address
     *            the address to set
     */
    public final void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param brief
     *            the brief to set
     */
    public final void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * @param builddate
     *            the builddate to set
     */
    public void setBuilddate(String builddate) {
        this.builddate = builddate;
    }

    /**
     * @param classkey
     *            the classkey to set
     */
    public final void setClasskey(String classkey) {
        this.classkey = classkey;
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
     * @param comurl
     *            the comurl to set
     */
    public final void setComurl(String comurl) {
        this.comurl = comurl;
    }

    /**
     * @param contact
     *            the contact to set
     */
    public final void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @param content
     *            the content to set
     */
    public final void setContent(String content) {
        this.content = content;
    }

    /**
     * @param createdate
     *            the createdate to set
     */
    public final void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @param credit
     *            the credit to set
     */
    public final void setCredit(Short credit) {
        this.credit = credit;
    }

    /**
     * @param email
     *            the email to set
     */
    public final void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public final void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @param faxarea
     *            the faxarea to set
     */
    public final void setFaxarea(String faxarea) {
        this.faxarea = faxarea;
    }

    /**
     * @param faxcountry
     *            the faxcountry to set
     */
    public final void setFaxcountry(String faxcountry) {
        this.faxcountry = faxcountry;
    }

    /**
     * @param fullPath
     *            the fullPath to set
     */
    public void setFullPath(String fullPath) {

        this.fullPath = fullPath;
        cutclass();
    }

    /**
     * @return the shortpath
     */
    public final String getShortpath() {
        return shortpath;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param infotype
     *            the infotype to set
     */
    public final void setInfotype(Short infotype) {
        this.infotype = infotype;
    }

    /**
     * @param keywords
     *            the keywords to set
     */
    public final void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * @param mainmode
     *            the mainmode to set
     */
    public void setMainmode(short mainmode) {
        this.mainmode = mainmode;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public final void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @param msncode
     *            the msncode to set
     */
    public final void setMsncode(String msncode) {
        this.msncode = msncode;
    }

    /**
     * @param phonearea
     *            the phonearea to set
     */
    public final void setPhonearea(String phonearea) {
        this.phonearea = phonearea;
    }

    /**
     * @param phonecountry
     *            the phonecountry to set
     */
    public final void setPhonecountry(String phonecountry) {
        this.phonecountry = phonecountry;
    }

    /**
     * @param picurl
     *            the picurl to set
     */
    public final void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * @param price
     *            the price to set
     */
    public final void setPrice(String price) {
        this.price = price;
    }

    /**
     * @param qqcode
     *            the qqcode to set
     */
    public final void setQqcode(String qqcode) {
        this.qqcode = qqcode;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public final void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @param regionkey
     *            the regionkey to set
     */
    public final void setRegionkey(String regionkey) {
        this.regionkey = regionkey;
    }

    /**
     * @param regionname
     *            the regionname to set
     */
    public final void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    /**
     * @param route
     *            the route to set
     */
    public final void setRoute(String route) {
        this.route = route;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public final void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @param title
     *            the title to set
     */
    public final void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @param url
     *            the url to set
     */
    public final void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param zipcode
     *            the zipcode to set
     */
    public final void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("address=" + address + "\n");
        sb.append("brief=" + brief + "\n");
        sb.append("builddate=" + builddate + "\n");
        sb.append("classkey=" + classkey + "\n");
        sb.append("itemname=" + itemname + "\n");
        sb.append("comurl=" + comurl + "\n");
        sb.append("contact=" + contact + "\n");
        sb.append("content=" + content + "\n");
        sb.append("createdate=" + createdate + "\n");
        sb.append("credit=" + credit + "\n");
        sb.append("email=" + email + "\n");
        sb.append("fax=" + fax + "\n");
        sb.append("faxarea=" + faxarea + "\n");
        sb.append("faxcountry=" + faxcountry + "\n");
        sb.append("fullPath=" + fullPath + "\n");
        sb.append("id=" + id + "\n");
        sb.append("infotype=" + infotype + "\n");
        sb.append("keywords=" + keywords + "\n");
        sb.append("mainmode=" + mainmode + "\n");
        sb.append("mobile=" + mobile + "\n");
        sb.append("msncode=" + msncode + "\n");
        sb.append("phonearea=" + phonearea + "\n");
        sb.append("phonecountry=" + phonecountry + "\n");
        sb.append("picurl=" + picurl + "\n");
        sb.append("price=" + price + "\n");
        sb.append("qqcode=" + qqcode + "\n");
        sb.append("quantity=" + quantity + "\n");
        sb.append("regionkey=" + regionkey + "\n");
        sb.append("regionname=" + regionname + "\n");
        sb.append("route=" + route + "\n");
        sb.append("tel=" + tel + "\n");
        sb.append("title=" + title + "\n");
        sb.append("type=" + type + "\n");
        sb.append("url=" + url + "\n");
        sb.append("zipcode=" + zipcode + "\n");
        sb.append("comid=" + comid + "\n");
        return sb.toString();
    }

}
