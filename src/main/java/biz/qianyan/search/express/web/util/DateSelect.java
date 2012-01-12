package biz.qianyan.search.express.web.util;

import java.util.Vector;

public class DateSelect {

    private static final Vector<LabelValueBean> dates = new Vector<LabelValueBean>(7);
    static {
        dates.add(new LabelValueBean("���з���ʱ��", -1));
        dates.add(new LabelValueBean("һ��֮��", 7));
        dates.add(new LabelValueBean("һ��֮��", 30));
        dates.add(new LabelValueBean("����֮��", 90));
        dates.add(new LabelValueBean("����֮��", 180));
        dates.add(new LabelValueBean("һ��֮��", 365));
        dates.add(new LabelValueBean("����֮��", 730));

    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getDates() {
        return dates;
    }

}
