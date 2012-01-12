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

        infosname.put(1, "��Ӧ");
        infosname.put(2, "��");
        infosname.put(3, "��Ʒ");
        infosname.put(4, "��Ѷ");
        infosname.put(5, "չ��");
        infosname.put(6, "��ְ");
        infosname.put(7, "��ҵ");
    }
}
