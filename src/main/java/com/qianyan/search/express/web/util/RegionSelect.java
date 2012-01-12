/**
 * 
 */
package com.qianyan.search.express.web.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Jock
 */
public class RegionSelect {
    private static final Log                          log           = LogFactory
                                                                            .getLog(RegionSelect.class);
    private Vector<LabelValueBean>                    provinces     = new Vector<LabelValueBean>(34);
    private Vector<LabelValueBean>                    provincesname = new Vector<LabelValueBean>(34);
    private Hashtable<String, Vector<LabelValueBean>> citys         = new Hashtable<String, Vector<LabelValueBean>>();

    public RegionSelect() {
        init();
    }

    private void init() {
        try {
            ObjectInputStream re = new ObjectInputStream(new FileInputStream(
                    "/data/search/WEB-INF/region.lib"));

            provinces = (Vector<LabelValueBean>) re.readObject();
            for (LabelValueBean lv : provinces) {
                log.info("Pro:" + lv.getLabel());
            }
            re.readUTF();
            citys = (Hashtable<String, Vector<LabelValueBean>>) re.readObject();
            re.readUTF();
            provincesname = (Vector<LabelValueBean>) re.readObject();
            re.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Vector<LabelValueBean> slist = new Vector<LabelValueBean>(1);
        slist.add(0, new LabelValueBean("µØ¼¶ÊÐ", "null"));
        citys.put("null", slist);
    }

    /**
     * @return the provinces
     */
    public Vector<LabelValueBean> getProvinces() {
        return provinces;
    }

    public Vector<LabelValueBean> getProvincesName() {
        return provincesname;
    }

    /**
     * @param provinces
     *            the provinces to set
     */
    public void setProvinces(Vector<LabelValueBean> provinces) {
        this.provinces = provinces;
    }

    public Vector<LabelValueBean> getCitys(String key) {
        return citys.get(key);
    }
}
