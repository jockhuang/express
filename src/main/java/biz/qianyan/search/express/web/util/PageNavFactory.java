package biz.qianyan.search.express.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.PatentForm;
import biz.qianyan.search.express.web.form.SearchForm;

public class PageNavFactory {
	private static final int MAXPAGE=20;
    private static String baseurl       = "/search.do?q=";
    private static String patentbaseurl = "/patent.do?q=";

    public static List<PageNav> getPagenav(SearchForm s, Navbar n) {
        List<PageNav> list = new ArrayList<PageNav>();
        if (s.getP() > 1) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=1");
            p.setLinkname("首页");
            p.setUrl(transfer(p, true));
            list.add(p);
        }
        if (n.getCurrpage() > 1) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=" + (n.getCurrpage() - 1));
            p.setLinkname("上一页");
            p.setUrl(transfer(p, true));
            list.add(p);
        }
        int start = n.getCurrpage() - 5;
        int end = n.getCurrpage() + 5;
        if (start <= 0) {
            end = end - start;
            start = 1;
        }
        if (end > n.getTotalpage()) {
            end = n.getTotalpage();
        }
        if (end > MAXPAGE) end = MAXPAGE;
        for (int i = start; i <= end; i++) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=" + i);
            p.setLinkname(String.valueOf(i));
            if (i == n.getCurrpage())
                p.setUrl(transfer(p, false));
            else
                p.setUrl(transfer(p, true));
            list.add(p);
        }
        if (n.getCurrpage() < MAXPAGE && n.getCurrpage() < n.getTotalpage()) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=" + (n.getCurrpage() + 1));
            p.setLinkname("下一页");
            p.setUrl(transfer(p, true));
            list.add(p);
        }
        return list;
    }

    private static String getUrl(SearchForm s) {
        StringBuffer sb = new StringBuffer(getKey(s));
        sb.append("&s=");
        sb.append(s.getS());
        return sb.toString();
    }

    public static String getKey(SearchForm s) {
        StringBuffer sb = new StringBuffer();
        sb.append(baseurl);
        sb.append(s.getEq());
        if ((s.getC() != null) && (!"".equals(s.getC().trim()))) {
            try {
                sb.append("&c=");
                sb.append(URLEncoder.encode(s.getC(), "GBK"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (s.getD() > 0) {
            sb.append("&d=");
            sb.append(s.getD());
        }
        if (s.getM() > 0) {
            sb.append("&m=");
            sb.append(s.getM());
        }
        if (s.getT() > 0) {
            sb.append("&t=");
            sb.append(s.getT());
        }
        if(s.getF()>0){
            sb.append("&f=");
            sb.append(s.getF());
        }
        if(s.getN()>0){
            sb.append("&n=");
            sb.append(s.getN());
        }
        if (s.getCapitalCN() != null && (!"null".equals(s.getCapitalCN()))) {
            sb.append("&capitalCN=");
            try {
				sb.append(URLEncoder.encode(s.getCapitalCN(), "GBK"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if (s.getProvinceCN() != null && (!"null".equals(s.getProvinceCN()))) {
            sb.append("&provinceCN=");
            
            try {
				sb.append(URLEncoder.encode(s.getProvinceCN(), "GBK"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return sb.toString();
    }

    public static List<PageNav> getPagenav(PatentForm s, Navbar n) {
        List<PageNav> list = new ArrayList<PageNav>();
        if (s.getP() > 1) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=1");
            p.setLinkname("首页");
            p.setUrl(transfer(p, true));
            list.add(p);
        }
        if (n.getCurrpage() > 1) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=" + (n.getCurrpage() - 1));
            p.setLinkname("上一页");
            p.setUrl(transfer(p, true));
            list.add(p);
        }
        int start = n.getCurrpage() - 5;
        int end = n.getCurrpage() + 5;
        if (start <= 0) {
            end = end - start;
            start = 1;
        }
        if (end > n.getTotalpage()) {
            end = n.getTotalpage();
        }
        if (end > MAXPAGE) end = MAXPAGE;
        for (int i = start; i <= end; i++) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=" + i);
            p.setLinkname(String.valueOf(i));
            if (i == n.getCurrpage())
                p.setUrl(transfer(p, false));
            else
                p.setUrl(transfer(p, true));
            list.add(p);
        }
        if (n.getCurrpage() < MAXPAGE && n.getCurrpage() < n.getTotalpage()) {
            PageNav p = new PageNav();
            p.setLink(getUrl(s) + "&p=" + (n.getCurrpage() + 1));
            p.setLinkname("下一页");
            p.setUrl(transfer(p, true));
            list.add(p);
        }
        return list;
    }

    private static String getUrl(PatentForm s) {
        StringBuffer sb = new StringBuffer(getKey(s));
        sb.append("&s=");
        sb.append(s.getS());
        return sb.toString();
    }

    public static String getKey(PatentForm s) {
        StringBuffer sb = new StringBuffer();
        sb.append(patentbaseurl);
        sb.append(s.getEq());

        sb.append("&c=");
        sb.append(s.getC());

        sb.append("&t=");
        sb.append(s.getT());
        if (s.getApplyd() > 0) {
            sb.append("&applyd=");
            sb.append(s.getApplyd());
        }
        if (s.getOpend() > 0) {
            sb.append("&opend=");
            sb.append(s.getOpend());
        }
        return sb.toString();
    }

    private static String transfer(PageNav p, boolean link) {
        String url = null;
        if (link)
            url = "<span class=\"style2\">[</span><span class=\"rr14\"><a href=\"" + p.getLink()
                    + "\" class=\"rr14\">" + p.getLinkname()
                    + "</a></span><span class=\"style2\">]</span> ";
        else
            url = "<span class=\"b14\">" + p.getLinkname() + "</span>";
        return url;
    }

}
