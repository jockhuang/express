/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package biz.qianyan.search.express.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import biz.qianyan.search.express.document.AdDocument;
import biz.qianyan.search.express.document.ClassResult;
import biz.qianyan.search.express.document.ExpressDocument;
import biz.qianyan.search.express.document.ReleatedKeyword;
import biz.qianyan.search.express.query.AdSearcher;
import biz.qianyan.search.express.query.ExpressSearcher;
import biz.qianyan.search.express.query.ReleatedSearcher;
import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.SearchForm;
import biz.qianyan.search.express.web.util.PageNavFactory;
import biz.qianyan.search.express.web.util.RegionSelect;

/**
 * MyEclipse Struts Creation date: 01-08-2008 XDoclet definition:
 * 
 * @struts.action path="/search" name="searchForm" input="/search.jsp" scope="request" validate="true"
 */
public class SearchAction extends Action {

    private static final Log log = LogFactory.getLog(SearchAction.class);
    /*
     * Generated Methods
     */
    private ExpressSearcher searcher;

    private AdSearcher adsearcher;

    private ReleatedSearcher releatedsearcher;

    private RegionSelect regionselect;

    /**
     * @param regionselect the regionselect to set
     */
    public void setRegionselect(RegionSelect regionselect) {
        this.regionselect = regionselect;
    }

    /**
     * Method execute
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        SearchForm searchForm = (SearchForm) form;
        searchForm.setProvinceselect(regionselect.getProvinces());
        if (searchForm.getProvinceCN() != null && (!"".equals(searchForm.getProvinceCN().trim()))
                && (!"null".equals(searchForm.getProvinceCN().trim()))) {
            log.info("get citys:" + searchForm.getProvinceCN());
            searchForm.setCitysselect(regionselect.getCitys(searchForm.getProvinceCN()));
        } else {
            log.info("get citys default" + regionselect.getCitys("null"));
            searchForm.setCitysselect(regionselect.getCitys("null"));
        }
        Integer curpage = searchForm.getP();
        if ((curpage == null) || (curpage <= 0)) {
            curpage = 1;
        }
        if (searchForm.getQ() == null || "".equals(searchForm.getQ().trim())) {
            return mapping.findForward("index");
        }

        ExpressSearcher mysearcher;

        mysearcher = searcher;
        // log.info("CapitalCN:"+searchForm.getCapitalCN());
        // log.info("ProvinceCN:"+searchForm.getProvinceCN());
        Navbar page = new Navbar(curpage);
        page.PAGESIZE = searchForm.getNum();
        log.info("pagesize=" + page.PAGESIZE);
        List<ExpressDocument> list = mysearcher.search(searchForm, page);
        log.info(searchForm.getQ() + " mini size:" + list.size());
        if (list.size() == 0 && searchForm.getN() == 1) {
            mysearcher = searcher;
            list = mysearcher.search(searchForm, page);
            log.info(searchForm.getQ() + "max size:" + list.size());
        }
        if (list.size() < page.PAGESIZE && searchForm.getN() == 1) {
            request.setAttribute("showmore", "true");
        }
        if (searchForm.getR() == 1) {
            for (ExpressDocument ed : list) {
                if (ed.getOrititle().length() > 14) {
                    ed.setTitle(ed.getOrititle().substring(0, 14) + "..");
                }
            }
            request.setAttribute("result", list);
            return mapping.findForward("left");
        }
        int cn = searchForm.getCn();
        if (cn < 0)
            cn = 1;
        if (searchForm.getR() == 2) {
            for (ExpressDocument ed : list) {
                if (ed.getBrief().length() > cn) {
                    ed.setBrief(ed.getBrief().substring(0, cn) + "..");
                }
            }
            request.setAttribute("form", searchForm);
            request.setAttribute("result", list);
            return mapping.findForward("diy");
        }
        List<ClassResult> clist = mysearcher.searchClass(searchForm, page);
        if (searchForm.getC() != null && searchForm.getC().trim().length() > 0) {
            request.setAttribute("filter", 1);
        } else {
            request.setAttribute("filter", 0);
        }
        List<AdDocument> textlist = adsearcher.searchText(searchForm, page);
        List<AdDocument> piclist = adsearcher.searchPic(searchForm, page);
        List<ReleatedKeyword> releatedlist = releatedsearcher.search(searchForm.getQ(), page);
        if (page.getAllrows() > 1000) {
            page.setAllrows((int) (page.getAllrows() * 1.5));
        }
        if (curpage == 1) {
            List<AdDocument> adlist = adsearcher.search(searchForm, page);
            for (int i = adlist.size(); i > 0; i--) {
                AdDocument ad = adlist.get(i - 1);
                log.info("adinfo:" + ad.toString());
                ExpressDocument ed = new ExpressDocument();
                log.info(ad.toString());
                try {
                    BeanUtils.copyProperties(ed, ad);
                    list.add(0, ed);
                    log.info("express info:" + ed.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (list.size() > 0) {
                ExpressDocument ed = list.get(0);
                ed.setBgcolor("#ebf1f2");
            }
        }
        request.setAttribute("pic", piclist);

        request.setAttribute("textadnum", textlist.size());
        request.setAttribute("text", textlist);
        request.setAttribute("page", page);
        request.setAttribute("pagenav", PageNavFactory.getPagenav(searchForm, page));
        request.setAttribute("result", list);
        request.setAttribute("form", searchForm);
        request.setAttribute("clist", clist);
        request.setAttribute("releated", releatedlist);
        request.setAttribute("releatedsize", releatedlist.size());
        if (searchForm.getT() == 3) {
            return mapping.findForward("company");
        }
        return mapping.findForward("result");
    }

    /**
     * @param searcher the searcher to set
     */
    public final void setSearcher(ExpressSearcher searcher) {
        this.searcher = searcher;
    }

    /**
     * @param adsearcher the adsearcher to set
     */
    public final void setAdsearcher(AdSearcher adsearcher) {
        this.adsearcher = adsearcher;
    }

    /**
     * @param releatedsearcher the releatedsearcher to set
     */
    public final void setReleatedsearcher(ReleatedSearcher releatedsearcher) {
        this.releatedsearcher = releatedsearcher;
    }

}
