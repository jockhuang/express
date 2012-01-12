package biz.qianyan.search.express.query.v2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import biz.qianyan.search.express.document.ClassResult;
import biz.qianyan.search.express.document.DocumentParser;
import biz.qianyan.search.express.document.ExpressDocument;
import biz.qianyan.search.express.query.ExpressQueryParser;
import biz.qianyan.search.express.query.ExpressSearcher;
import biz.qianyan.search.express.query.FilterFactory;
import biz.qianyan.search.express.query.HighLighter;
import biz.qianyan.search.express.query.KeywordFormat;
import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.SearchForm;

/**
 * @author Jock
 */
public class ExpressSearcherImpl implements ExpressSearcher {

    private static final Log log = LogFactory.getLog(ExpressSearcherImpl.class);

    private DocumentParser docparser;

    private HighLighter highlighter;

    private String[] indexdir;

    // private String field;

    private ExpressQueryParser parser;
    // private MultiFieldQueryParser multiparser;

    // private Analyzer analyzer;

    private MultiReader reader;

    private MultiSearcher searcher;

    /**
     * @param parser
     * @param indexdir
     * @param analyzer
     * @param docparser
     */
    public ExpressSearcherImpl(String[] indexdir, ExpressQueryParser parser, DocumentParser docparser,
            HighLighter highlighter) {

        this.parser = parser;

        this.indexdir = indexdir;
        this.highlighter = highlighter;
        this.docparser = docparser;
        try {
            IndexSearcher[] searchers = new IndexSearcher[indexdir.length];
            IndexReader[] readers = new IndexReader[indexdir.length];
            for (int i = 0; i < indexdir.length; i++) {
                log.info("Open index file:" + indexdir[i]);
                readers[i] = IndexReader.open(FSDirectory.open(new File(indexdir[i])));
                searchers[i] = new IndexSearcher(readers[i]);
            }
            reader = new MultiReader(readers);
            searcher = new MultiSearcher(searchers);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.fillInStackTrace());
        }
    }

    public void reOpen() {
        try {
            IndexSearcher[] searchers = new IndexSearcher[indexdir.length];
            IndexReader[] readers = new IndexReader[indexdir.length];
            for (int i = 0; i < indexdir.length; i++) {
                log.info("ReOpen index file:" + indexdir[i]);
                readers[i] = IndexReader.open(FSDirectory.open(new File(indexdir[i])));
                searchers[i] = new IndexSearcher(readers[i]);
            }
            reader = new MultiReader(readers);
            searcher = new MultiSearcher(searchers);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.fillInStackTrace());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hezon.search.query.Searcher#search(java.lang.String)
     */
    public List<ExpressDocument> search(SearchForm s, Navbar page) {
        List<ExpressDocument> list = new ArrayList<ExpressDocument>();
        if (s.getQ() == null || "".equals(s.getQ().trim())) {
            return list;
        }
        String keyword = s.getQ().trim();

        keyword = KeywordFormat.convertKeyword(keyword);
        if (keyword.equals("")) {
            return list;
        }
        log.info("search:" + keyword);
        Sort sort = null;
        boolean needredo = true;
        boolean needreload = true;
        if (s.getS() == 1) {
            sort = new Sort(new SortField[] { new SortField("createdate", SortField.STRING, true) });
            // sort = new Sort("createdate", true);
            if (s.getP() == 1 & s.getT() == 0)
                needredo = true;
        }
        try {

            Query query = null;
            if (1 == s.getF()) {
                query = parser.titleparse(keyword);
            } else {
                query = parser.parse(keyword);
            }
            TopDocs hits = null;
            TopDocs hits2 = null;

            Filter filter = FilterFactory.getFilter(s);
            Filter filter2 = FilterFactory.getCompanyFilter(s);
            if (filter == null) {
                hits = searcher.search(query, page.PAGESIZE, sort);
                if (needredo)
                    hits2 = searcher.search(query, page.PAGESIZE);
            } else {
                hits = searcher.search(query, filter, page.PAGESIZE, sort);
                if (needredo)
                    hits2 = searcher.search(query, filter2, page.PAGESIZE);
            }

            page.setAllrows(hits.totalHits);
            int start = page.getStart();

            int end = Math.min(hits.totalHits, start + page.PAGESIZE);

            int maxsearch = Math.min(hits.totalHits, 200);
            int i = 0, j = 0;
            int length = page.PAGESIZE;
            HashMap<String, Integer> idtable = new HashMap<String, Integer>();

            int size = list.size();
            if (needreload || page.getCurrpage() > 1) {
                ScoreDoc scoreDocs[] = hits.scoreDocs;
                for (ScoreDoc scoreDoc : scoreDocs) {
                    Document doc = searcher.doc(scoreDoc.doc);

                    ExpressDocument im = DocumentParser.convert(doc);
                    try {
                        if (s.getR() == 0) {
                            String title = highlighter.highlight(im.getTitle(), keyword, false);
                            if (title != null) {
                                im.setTitle(title);
                            }
                            String brief = highlighter.highlight(doc.get("brief"), keyword, true);

                            if (brief != null) {
                                im.setBrief(brief);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    if (needreload) {
                        if (!idtable.containsKey(doc.get("id"))) {
                            list.add(im);
                            size++;

                        }
                    } else {
                        list.add(im);
                        size++;
                    }
                    if (size >= page.PAGESIZE)
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.fillInStackTrace());
        }
        return list;
    }

    public List<ClassResult> searchClass(SearchForm s, Navbar page) {
        List<ClassResult> list = new ArrayList<ClassResult>();
        if (s.getQ() == null || "".equals(s.getQ().trim())) {
            return list;
        }

        return list;
    }

}
