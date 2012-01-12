/**
 * 
 */
package biz.qianyan.search.express.document;

import java.net.URLEncoder;

/**
 * @author Jock
 */
public class ReleatedKeyword {
    private String keyword;
    private String encodekeyword;

    public ReleatedKeyword() {
        super();
    }

    public ReleatedKeyword(String keyword) {
        super();
        this.keyword = keyword;
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
        convert();
    }

    /**
     * @return the encodekeyword
     */
    public final String getEncodekeyword() {
        return encodekeyword;
    }

    private void convert() {
        try {
            this.encodekeyword = URLEncoder.encode(keyword, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
