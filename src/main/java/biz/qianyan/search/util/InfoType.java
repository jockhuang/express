/**
 * 
 */
package biz.qianyan.search.util;

import java.util.Hashtable;

/**
 * @author jock
 */
public class InfoType {

    private static Hashtable<Integer, String> infosname;

    static {
        init();
    }

    public static String getInfoName(Integer info) {
        return infosname.get(info) == null ? "" : infosname.get(info);
    }

    private static void init() {
        infosname = new Hashtable<Integer, String>();

        infosname.put(1, "供应");
        infosname.put(2, "求购");
        infosname.put(3, "产品");
        infosname.put(4, "资讯");
        infosname.put(5, "展会");
        infosname.put(6, "求职");
        infosname.put(7, "企业");
    }
}
