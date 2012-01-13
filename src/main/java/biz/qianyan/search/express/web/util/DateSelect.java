package biz.qianyan.search.express.web.util;

import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

public class DateSelect {

    private static final Vector<LabelValueBean> dates = new Vector<LabelValueBean>(7);
    static {
        dates.add(new LabelValueBean("所有发布时间", String.valueOf(-1)));
        dates.add(new LabelValueBean("一周之内", String.valueOf(7)));
        dates.add(new LabelValueBean("一月之内", String.valueOf(30)));
        dates.add(new LabelValueBean("三月之内", String.valueOf(90)));
        dates.add(new LabelValueBean("半年之内", String.valueOf(180)));
        dates.add(new LabelValueBean("一年之内", String.valueOf(365)));
        dates.add(new LabelValueBean("两年之内", String.valueOf(730)));

    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getDates() {
        return dates;
    }

}
