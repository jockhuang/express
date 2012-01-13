package biz.qianyan.search.express.web.util;

import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

public class BizModeSelect {

    private static final Vector<LabelValueBean> modes = new Vector<LabelValueBean>(6);
    static {
        modes.add(new LabelValueBean("ѡ��Ӫģʽ", String.valueOf(-1)));
        modes.add(new LabelValueBean("����/����", String.valueOf(1)));
        modes.add(new LabelValueBean("����", String.valueOf(4)));
        modes.add(new LabelValueBean("ó��", String.valueOf(7)));
        modes.add(new LabelValueBean("��������", String.valueOf(8)));
        modes.add(new LabelValueBean("��֯����/����", String.valueOf(5)));
    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getModes() {
        return modes;
    }
}
