/**
 * 
 */
package com.qianyan.search.express.query.v1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.qianyan.search.express.document.DocumentParser;
import com.qianyan.search.express.document.ReleatedKeyword;
import com.qianyan.search.express.query.ReleatedSearcher;
import com.qianyan.search.express.web.Navbar;

/**
 * @author Jock
 */
public class ReleatedSearcherImpl implements ReleatedSearcher {

    private static final Log log = LogFactory.getLog(ReleatedSearcherImpl.class);
    private DocumentParser docparser;

    private String[] indexdir;

    private QueryParser parser;
    // private MultiFieldQueryParser multiparser;

    // private Analyzer analyzer;

    private MultiReader reader;

    private MultiSearcher searcher;

    public ReleatedSearcherImpl(String[] indexdir, Analyzer analyzer, DocumentParser docparser) {

        this.parser = new QueryParser(Version.LUCENE_35, "keyword", analyzer);

        this.indexdir = indexdir;

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

    /*
     * (non-Javadoc)
     * 
     * @see com.qianyan.search.express.query.ReleatedSearcher#search(com.qianyan.search.express.web.form.SearchForm,
     * com.qianyan.search.express.web.Navbar)
     */
    @Override
    public List<ReleatedKeyword> search(String s, Navbar page) {
        List<ReleatedKeyword> list = new ArrayList<ReleatedKeyword>();
        if (s == null || "".equals(s.trim())) {
            return list;
        }
        String keyword = s.trim();
        // keyword = KeywordFormat.convertKeyword(keyword);
        // log.info("search releated:" + keyword);
        try {
            Query query = parser.parse(keyword);
            
            int length = 0;
            TopDocs topDocs = searcher.search(query, 10);

            ScoreDoc scoreDocs[] = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                // log.info("releated size:" + hits.length());

                Document doc = searcher.doc(scoreDoc.doc);
                ReleatedKeyword ad = DocumentParser.convertReleated(doc);
                if (!keyword.equals(ad.getKeyword())) {
                    list.add(ad);
                    length++;
                }
                if (length > 10)
                    break;
            }
        } catch (Exception e) {
            log.error("ERR:" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

}
