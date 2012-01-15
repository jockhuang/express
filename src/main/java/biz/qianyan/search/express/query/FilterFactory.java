package biz.qianyan.search.express.query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.ChainedFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.PrefixFilter;
import org.apache.lucene.search.TermsFilter;

import biz.qianyan.search.express.web.form.PatentForm;
import biz.qianyan.search.express.web.form.SearchForm;

public class FilterFactory {
    public static Filter getFilter(PatentForm s) {
        List<Filter> filterslist = new ArrayList<Filter>();
        if (s.getC() > 0) {
            TermsFilter filter = new TermsFilter();
            filter.addTerm(new Term("classes", String.valueOf(s.getC())));
            filterslist.add(filter);
        }
        if (s.getProvince() != null && (!"-1".equals(s.getProvince())) && (!"null".equals(s.getProvince()))) {
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
        } else {
            return null;
        }
    }

    public static Filter getFilter(SearchForm s) {
        List<Filter> filterslist = new ArrayList<Filter>();
        if ((s.getC() != null) && (!"".equals(s.getC().trim()))) {
            TermsFilter filter = new TermsFilter();
            filter.addTerm(new Term("fullpath", s.getC()));
            filterslist.add(filter);
        }
        if (s.getD() > 0) {
            Date now = new Date();
            long start = getStart(s.getD());
            long end = now.getTime();
            Filter filter = NumericRangeFilter.newLongRange("createdate",  start,  end, true, true);

            filterslist.add(filter);
        }
        if (s.getM() > 0) {
            Filter filter = NumericRangeFilter.newIntRange("mainmode",  s.getM(),  s.getM(), true, true);
            
            filterslist.add(filter);
        }

        if (s.getCapitalCN() != null && (!"-1".equals(s.getCapitalCN()) && (!"null".equals(s.getCapitalCN())))) {
            Term term = new Term("regionkey", s.getCapitalCN());
            Filter filter = new PrefixFilter(term);
            filterslist.add(filter);
        } else if (s.getProvinceCN() != null && (!"-1".equals(s.getProvinceCN()))
                && (!"null".equals(s.getProvinceCN()))) {
            Term term = new Term("regionkey", s.getProvinceCN());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (s.getY() > 0) {
            Term term = new Term("builddate", s.getY() + "");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (s.isPic()) {
            Term term = new Term("picurl", "http");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (s.getT() > 0) {
            switch (s.getT()) {
            case 1:
                Filter filter1 = NumericRangeFilter.newIntRange("type",  3,  3, true, true);
                filterslist.add(filter1);
                break;
            case 2:
                Filter filter2 = NumericRangeFilter.newIntRange("type",  1,  1, true, true);
                filterslist.add(filter2);
                break;
            case 3:

                Filter filter3 = NumericRangeFilter.newIntRange("type",  2,  2, true, true);
                filterslist.add(filter3);
                break;
            case 4:

                Filter filter4 = NumericRangeFilter.newIntRange("type",  3,  3, true, true);
                filterslist.add(filter4);

                Filter filter41 = NumericRangeFilter.newIntRange("infotype",  2,  2, true, true);
                filterslist.add(filter41);
                break;
            case 5:

                Filter filter5 = NumericRangeFilter.newIntRange("type",  3,  3, true, true);
                filterslist.add(filter5);

                Filter filter51 = NumericRangeFilter.newIntRange("infotype",  1,  1, true, true);
                filterslist.add(filter51);
                break;
            }
        }
        if (filterslist.size() > 0) {
            Filter[] filters = new Filter[filterslist.size()];
            Filter filter = new ChainedFilter(filterslist.toArray(filters), ChainedFilter.AND);
            return filter;
        } else {
            return null;
        }
    }

    public static Filter getCompanyFilter(SearchForm s) {
        List<Filter> filterslist = new ArrayList<Filter>();
        if ((s.getC() != null) && (!"".equals(s.getC().trim()))) {
            TermsFilter filter = new TermsFilter();
            filter.addTerm(new Term("fullpath", s.getC()));
            filterslist.add(filter);
        }
        if (s.getD() > 0) {
            Date now = new Date();
            long start = getStart(s.getD());
            long end = now.getTime();
            Filter filter = NumericRangeFilter.newLongRange("createdate",  start,  end, true, true);

            filterslist.add(filter);
        }
        if (s.getM() > 0) {
            Filter filter = NumericRangeFilter.newIntRange("mainmode",  s.getM(),  s.getM(), true, true);
            filterslist.add(filter);
        }

        if (s.getCapitalCN() != null && (!"null".equals(s.getCapitalCN()))) {
            Term term = new Term("regionkey", s.getCapitalCN());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        } else if (s.getProvinceCN() != null && (!"null".equals(s.getProvinceCN()))) {
            Term term = new Term("regionkey", s.getProvinceCN());
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        if (s.getY() > 0) {
            Term term = new Term("builddate", s.getY() + "");
            PrefixFilter filter = new PrefixFilter(term);
            filterslist.add(filter);
        }
        Filter filter3 = NumericRangeFilter.newIntRange("type",  2,  2, true, true);
        filterslist.add(filter3);

        if (filterslist.size() > 0) {
            Filter[] filters = new Filter[filterslist.size()];
            Filter filter = new ChainedFilter(filterslist.toArray(filters), ChainedFilter.AND);
            return filter;
        } else {
            return null;
        }
    }

    private static long getStart(int d) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 0 - d);
        return cal.getTimeInMillis();
    }
}
