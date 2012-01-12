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
        years.add(new LabelValueBean("所有注册年份", -1));
        years.add(new LabelValueBean("超过5年", 4));
        years.add(new LabelValueBean("超过3年", 3));
        years.add(new LabelValueBean("超过1年", 2));
        years.add(new LabelValueBean("不满1年", 1));

    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getYears() {
        return years;
    }
}
