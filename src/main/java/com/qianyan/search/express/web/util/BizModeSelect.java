package com.qianyan.search.express.web.util;

import java.util.Vector;

public class BizModeSelect {

    private static final Vector<LabelValueBean> modes = new Vector<LabelValueBean>(6);
    static {
        modes.add(new LabelValueBean("ѡ��Ӫģʽ", -1));
        modes.add(new LabelValueBean("����/����", 1));
        modes.add(new LabelValueBean("����", 4));
        modes.add(new LabelValueBean("ó��", 7));
        modes.add(new LabelValueBean("��������", 8));
        modes.add(new LabelValueBean("��֯����/����", 5));
    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getModes() {
        return modes;
    }
}
