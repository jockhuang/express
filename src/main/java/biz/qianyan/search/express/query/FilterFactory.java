package biz.qianyan.search.express.query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.ChainedFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.PrefixFilter;
import org.apache.lucene.search.TermRangeFilter;

import biz.qianyan.search.express.web.form.PatentForm;
import biz.qianyan.search.express.web.form.SearchForm;

public class FilterFactory {
    public static Filter getFilter(PatentForm s) {
        List<Filter> filterslist = new ArrayList<Filter>();
        if (s.getC() > 0) {
            ExactFilter filter = new ExactFilter("classes", s.getC() + "");
            filterslist.add(filter);
        }
        if (s.getProvince() != null && (!"-1".equals(s.getProvince()))
                && (!"null".equals(s.getProvince()))) {
            Term term = new Term("country", s.getProvince());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (s.getOpend() > 0) {
            Term term = new Term("publicdate", s.getOpend() + "");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (s.getApplyd() > 0) {
            Term term = new Term("applydate", s.getApplyd() + "");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (filterslist.size() > 0) {
            Filter[] filters = new Filter[filterslist.size()];
            Filter filter = new ChainedFilter(filterslist.toArray(filters), ChainedFilter.AND);
            return filter;
        }
        else {
            return null;
        }
    }

    public static Filter getFilter(SearchForm s) {
        List<Filter> filterslist = new ArrayList<Filter>();
        if ((s.getC() != null) && (!"".equals(s.getC().trim()))) {
            ExactFilter filter = new ExactFilter("fullpath", s.getC());
            filterslist.add(filter);
        }
        if (s.getD() > 0) {
            Date now = new Date();
            String start = getStart(s.getD());
            String end = DateTools.timeToString(now.getTime(), DateTools.Resolution.MINUTE);
            TermRangeFilter filter = new TermRangeFilter("createdate", start, end, true, true);

            filterslist.add(filter);
        }
        if (s.getM() > 0) {
            ExactFilter filter = new ExactFilter("mainmode", s.getM() + "");
            filterslist.add(filter);
        }

        if (s.getCapitalCN() != null
                && (!"-1".equals(s.getCapitalCN()) && (!"null".equals(s.getCapitalCN())))) {
            Term term = new Term("regionkey", s.getCapitalCN());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        else if (s.getProvinceCN() != null && (!"-1".equals(s.getProvinceCN()))
                && (!"null".equals(s.getProvinceCN()))) {
            Term term = new Term("regionkey", s.getProvinceCN());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if(s.getY()>0){
            Term term = new Term("builddate", s.getY()+"");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if(s.isPic()){
            Term term = new Term("picurl", "http");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (s.getT() > 0) {
            switch (s.getT()) {
                case 1:
                    ExactFilter filter1 = new ExactFilter("type", 3 + "");
                    filterslist.add(filter1);
                    break;
                case 2:
                    ExactFilter filter2 = new ExactFilter("type", 1 + "");
                    filterslist.add(filter2);
                    break;
                case 3:
                    
                    ExactFilter filter3 = new ExactFilter("type", 2 + "");
                    filterslist.add(filter3);
                    break;
                case 4:
                    
                    ExactFilter filter4 = new ExactFilter("type", 3 + "");
                    filterslist.add(filter4);
                    
                    ExactFilter filter41 = new ExactFilter("infotype", 2 + "");
                    filterslist.add(filter41);
                    break;
                case 5:
                    
                    ExactFilter filter5 = new ExactFilter("type", 3 + "");
                    filterslist.add(filter5);
                    
                    ExactFilter filter51 = new ExactFilter("infotype", 1 + "");
                    filterslist.add(filter51);
                    break;
            }
        }
        if (filterslist.size() > 0) {
            Filter[] filters = new Filter[filterslist.size()];
            Filter filter = new ChainedFilter(filterslist.toArray(filters), ChainedFilter.AND);
            return filter;
        }
        else {
            return null;
        }
    }

    public static Filter getCompanyFilter(SearchForm s) {
        List<Filter> filterslist = new ArrayList<Filter>();
        if ((s.getC() != null) && (!"".equals(s.getC().trim()))) {
            ExactFilter filter = new ExactFilter("fullpath", s.getC());
            filterslist.add(filter);
        }
        if (s.getD() > 0) {
            Date now = new Date();
            String start = getStart(s.getD());
            String end = DateTools.timeToString(now.getTime(), DateTools.Resolution.MINUTE);
            TermRangeFilter filter = new TermRangeFilter("createdate", start, end, true, true);

            filterslist.add(filter);
        }
        if (s.getM() > 0) {
            ExactFilter filter = new ExactFilter("mainmode", s.getM() + "");
            filterslist.add(filter);
        }

        if (s.getCapitalCN() != null && (!"null".equals(s.getCapitalCN()))) {
            Term term = new Term("regionkey", s.getCapitalCN());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        else if (s.getProvinceCN() != null && (!"null".equals(s.getProvinceCN()))) {
            Term term = new Term("regionkey", s.getProvinceCN());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if(s.getY()>0){
            Term term = new Term("builddate", s.getY()+"");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        ExactFilter filter3 = new ExactFilter("type", 2 + "");
        filterslist.add(filter3);

        if (filterslist.size() > 0) {
            Filter[] filters = new Filter[filterslist.size()];
            Filter filter = new ChainedFilter(filterslist.toArray(filters), ChainedFilter.AND);
            return filter;
        }
        else {
            return null;
        }
    }

    private static String getStart(int d) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 0 - d);
        return DateTools.timeToString(cal.getTimeInMillis(), DateTools.Resolution.MINUTE);
    }
}