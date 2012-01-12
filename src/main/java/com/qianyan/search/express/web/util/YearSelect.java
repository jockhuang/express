/**
 * 
 */
package com.qianyan.search.express.web.util;

import java.util.Vector;

/**
 * @author Jock
 */
public class YearSelect {
    private static final Vector<LabelValueBean> years = new Vector<LabelValueBean>(7);
    static {
        years.add(new LabelValueBean("����ע�����", -1));
        years.add(new LabelValueBean("����5��", 4));
        years.add(new LabelValueBean("����3��", 3));
        years.add(new LabelValueBean("����1��", 2));
        years.add(new LabelValueBean("����1��", 1));

    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getYears() {
        return years;
    }
}
