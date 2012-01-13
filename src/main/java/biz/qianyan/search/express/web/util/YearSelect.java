/**
 * 
 */
package biz.qianyan.search.express.web.util;

import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

/**
 * @author Jock
 */
public class YearSelect {
    private static final Vector<LabelValueBean> years = new Vector<LabelValueBean>(7);
    static {
        years.add(new LabelValueBean("����ע�����", String.valueOf(-1)));
        years.add(new LabelValueBean("����5��", String.valueOf(4)));
        years.add(new LabelValueBean("����3��", String.valueOf(3)));
        years.add(new LabelValueBean("����1��", String.valueOf(2)));
        years.add(new LabelValueBean("����1��", String.valueOf(1)));

    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getYears() {
        return years;
    }
}
