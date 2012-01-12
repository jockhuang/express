package biz.qianyan.search.util;

import java.util.Hashtable;

/**
 * @author jock
 */
public class BizMode {

    private static Hashtable<Integer, String> modename;

    /**
     * 
     */
    static {
        init();
    }

    public static String getModename(Integer mode) {
        if (modename == null) {
            init();
        }
        return modename.get(mode) == null ? "" : modename.get(mode);
    }

    private static void init() {
        modename = new Hashtable<Integer, String>();
        modename.put(1, "有限公司");
        modename.put(2, "个体");
    }
}
