package biz.qianyan.search.express.web.util;

public class PageNav {

    private String link;
    private String linkname;

    private String url;

    // private static String baseurl = "/search.do?";

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @return the linkname
     */
    public String getLinkname() {
        return linkname;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(String link) {
        this.link = link;

    }

    /**
     * @param linkname
     *            the linkname to set
     */
    public void setLinkname(String linkname) {
        this.linkname = linkname;

    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
