package biz.qianyan.search.patent.query.v2;

import java.io.File;
import java.util.ArrayList;
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
import biz.qianyan.search.express.query.FilterFactory;
import biz.qianyan.search.express.query.HighLighter;
import biz.qianyan.search.express.query.KeywordFormat;
import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.PatentForm;
import biz.qianyan.search.patent.document.DocumentParser;
import biz.qianyan.search.patent.document.PatentDocument;
import biz.qianyan.search.patent.query.PatentQueryParser;
import biz.qianyan.search.patent.query.PatentSearcher;

/**
 * 
 * 
 * @author Jock
 */
public class PatentSearchImpl implements PatentSearcher {
    private static final Log log = LogFactory.getLog(PatentSearchImpl.class);

    private DocumentParser docparser;

    private HighLighter highlighter;

    private String[] indexdir;

    // private String field;

    private PatentQueryParser parser;
    // private MultiFieldQueryParser multiparser;

    // private Analyzer analyzer;

    private MultiReader reader;

    private MultiSearcher searcher;

    public PatentSearchImpl(String[] indexdir, PatentQueryParser parser, DocumentParser docparser,
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

    @Override
    public List<PatentDocument> search(PatentForm s, Navbar page) {
        List<PatentDocument> list = new ArrayList<PatentDocument>();
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

        if (s.getS() == 1) {
            sort = new Sort(new SortField[] { new SortField("applydate", SortField.STRING, true) });
            // sort = new Sort("applydate", false);
        }
        try {

            Query query = null;
            if (1 == s.getT()) {
                query = parser.titleparse(keyword);
            } else {
                query = parser.parse(keyword);
            }
            log.info("sort=" + sort);
            TopDocs hits = null;
            Filter filter = FilterFactory.getFilter(s);
            if (filter == null) {
                hits = searcher.search(query, page.PAGESIZE, sort);
            } else {
                hits = searcher.search(query, filter, page.PAGESIZE, sort);
            }
            log.info("result size=" + hits.totalHits);
            page.setAllrows(hits.totalHits);
            int start = page.getStart();

            int end = Math.min(hits.totalHits, start + page.PAGESIZE);

            int i = 0;
            ScoreDoc scoreDocs[] = hits.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {

                Document doc = searcher.doc(scoreDoc.doc);

                PatentDocument im = DocumentParser.convert(doc);
                try {

                    String brief = highlighter.highlight(doc.get("brief"), keyword, true);

                    if (brief != null) {
                        im.setBrief(brief);
                    }
                    String title = highlighter.highlight(doc.get("title"), keyword, false);
                    if (title != null) {
                        im.setColortitle(title);
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }

                list.add(im);

            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.fillInStackTrace());
        }
        return list;
    }

    @Override
    public List<ClassResult> searchClass(PatentForm s, Navbar page) {
        // TODO Auto-generated method stub
        return null;
    }

}
