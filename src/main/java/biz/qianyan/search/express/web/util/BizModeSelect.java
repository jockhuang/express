package biz.qianyan.search.express.web.util;

import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

public class BizModeSelect {

    private static final Vector<LabelValueBean> modes = new Vector<LabelValueBean>(6);
    static {
        modes.add(new LabelValueBean("选择经营模式", String.valueOf(-1)));
        modes.add(new LabelValueBean("生产/制造", String.valueOf(1)));
        modes.add(new LabelValueBean("服务", String.valueOf(4)));
        modes.add(new LabelValueBean("贸易", String.valueOf(7)));
        modes.add(new LabelValueBean("政府机构", String.valueOf(8)));
        modes.add(new LabelValueBean("组织团体/其他", String.valueOf(5)));
    }

    /**
     * @return the dates
     */
    public static Vector<LabelValueBean> getModes() {
        return modes;
    }
}
