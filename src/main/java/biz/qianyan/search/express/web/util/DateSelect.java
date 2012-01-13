package biz.qianyan.search.express.web.util;

import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

public class DateSelect {

    private static final Vector<LabelValueBean> dates = new Vector<LabelValueBean>(7);
    static {
        dates.add(new LabelValueBean("���з���ʱ��", String.valueOf(-1)));
        dates.add(new LabelValueBean("һ��֮��", String.valueOf(7)));
        dates.add(new LabelValueBean("һ��֮��", String.valueOf(30)));
        dates.add(new LabelValueBean("����֮��", String.valueOf(90)));
        dates.add(new LabelValueBean("����֮��", String.valueOf(180)));
        dates.add(new LabelValueBean("һ��֮��", String.valueOf(365)));
        dates.add(new LabelValueBean("����֮��", String.valueOf(730)));

    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getDates() {
        return dates;
    }

}
