/**
 * 
 */
package biz.qianyan.search.express.index;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import biz.qianyan.search.db.model.Region;
import biz.qianyan.search.db.model.RegionDAO;
import biz.qianyan.search.util.Config;

/**
 * @author Jock
 */
public final class RegionFileMake {

    private static final Log log = LogFactory.getLog(RegionFileMake.class);
    private RegionDAO dao;

    private Vector<LabelValueBean> provinces = new Vector<LabelValueBean>(34);
    private Vector<LabelValueBean> provincesname = new Vector<LabelValueBean>(34);
    private Hashtable<String, Vector<LabelValueBean>> citys = new Hashtable<String, Vector<LabelValueBean>>();

    /**
     * @return the dao
     */
    public RegionDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(RegionDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the provinces
     */
    public Vector<LabelValueBean> getProvinces() {
        return provinces;
    }

    /**
     * @param provinces the provinces to set
     */
    public void setProvinces(Vector<LabelValueBean> provinces) {
        this.provinces = provinces;
    }

    /**
     * @return the citys
     */
    public Hashtable<String, Vector<LabelValueBean>> getCitys() {
        return citys;
    }

    /**
     * @param citys the citys to set
     */
    public void setCitys(Hashtable<String, Vector<LabelValueBean>> citys) {
        this.citys = citys;
    }

    public void initFile() {
        List<Region> plist = dao.findProvince();
        provinces.add(0, new LabelValueBean("国家或省份", "null"));
        provincesname.add(0, new LabelValueBean("国家或省份", "null"));
        for (Region r : plist) {
            LabelValueBean lv = new LabelValueBean(r.getClassname(), r.getClasskey());
            LabelValueBean lv1 = new LabelValueBean(r.getClassname(), r.getClassname());
            log.info("add province:" + r.getClassname());
            provinces.add(lv);
            provincesname.add(lv1);
            List<Region> clist = dao.findCity(r.getClasskey());
            if (clist.size() == 1) {
                Region vr = clist.get(0);
                clist = dao.findCity(vr.getClasskey());
            }
            Vector<LabelValueBean> slist = new Vector<LabelValueBean>(10);
            slist.add(0, new LabelValueBean("地级市", "null"));
            for (Region c : clist) {

                LabelValueBean clv = new LabelValueBean(c.getClassname(), c.getClasskey());
                log.info("add city:" + c.getClassname());
                slist.add(clv);
            }
            citys.put(r.getClasskey(), slist);
        }
        try {
            ObjectOutputStream re = new ObjectOutputStream(new FileOutputStream(Config.REGIONFILE));

            re.writeObject(provinces);
            re.writeUTF("\n");
            re.writeObject(citys);
            re.writeUTF("\n");
            re.writeObject(provincesname);
            re.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] s = new String[2];
        s[0] = "searchContext.xml";
        s[1] = "indexContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(s);
        RegionFileMake rf = (RegionFileMake) ctx.getBean("RegionFileMake");
        rf.initFile();
    }

}
