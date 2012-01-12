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
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.ChainedFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.qianyan.search.express.document.AdDocument;
import com.qianyan.search.express.document.DocumentParser;
import com.qianyan.search.express.query.AdSearcher;
import com.qianyan.search.express.query.ExactFilter;
import com.qianyan.search.express.web.Navbar;
import com.qianyan.search.express.web.form.SearchForm;

/**
 * @author Jock
 */
public class AdSearcherImpl implements AdSearcher {

    private static final Log log = LogFactory.getLog(AdSearcherImpl.class);
    private DocumentParser   docparser;

    private String[]         indexdir;

    private QueryParser      parser;
    // private MultiFieldQueryParser multiparser;

    // private Analyzer analyzer;

    private MultiReader      reader;

    private MultiSearcher    searcher;

    public AdSearcherImpl(String[] indexdir, Analyzer analyzer, DocumentParser docparser) {

        this.parser = new QueryParser(Version.LUCENE_35,"keyword", new SimpleAnalyzer(Version.LUCENE_35));

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

    private String getSearchType(int searchtype) {
        switch (searchtype) {
            case 0:// ȫ��
                return "30";
            case 5:// ��Ӧ
                return "1";
            case 4:// ��
                return "2";
            case 2:// ��Ʒ
                return "10";
            case 3:// ��ҵ
                return "20";
            default:
                return "0";
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qianyan.search.express.query.AdSearcher#search(com.qianyan.search.express.web.form.SearchForm,
     *      com.qianyan.search.express.web.Navbar) type:������� 0�Ҳ�ͼƬ��� 1���̶������� 2������������ searchtype:��������Ͷ��λ��,
     *      ��ʾ��ǰ��潫Ͷ�ŵ��ĸ������� 0����ͨͶ 30ȫ������ 1��Ӧ���� 2������ 10��Ʒ���� 20��ҵ���� infotype:��Ϣ���� 1�̻� 10��Ʒ 20��ҵ
     */

    @Override
    public List<AdDocument> search(SearchForm s, Navbar page) {
        List<AdDocument> list = new ArrayList<AdDocument>();
        if (s.getQ() == null || "".equals(s.getQ().trim())) {
            return list;
        }
        String keyword = s.getQ().trim();
        log.info("search ad:" + keyword);
        String searchtype = getSearchType(s.getT());
        return searchIndex(keyword, searchtype, "1");
    }

    @Override
    public List<AdDocument> searchText(SearchForm s, Navbar page) {
        List<AdDocument> list = new ArrayList<AdDocument>();
        if (s.getQ() == null || "".equals(s.getQ().trim())) {
            return list;
        }
        String keyword = s.getQ().trim();
        log.info("search text:" + keyword);
        String searchtype = getSearchType(s.getT());
        return searchIndex(keyword, searchtype, "2");
    }

    @Override
    public List<AdDocument> searchPic(SearchForm s, Navbar page) {
        List<AdDocument> list = new ArrayList<AdDocument>();
        if (s.getQ() == null || "".equals(s.getQ().trim())) {
            return list;
        }
        String keyword = s.getQ().trim();
        log.info("search pic:" + keyword);
        String searchtype = getSearchType(s.getT());
        return searchIndex(keyword, searchtype, "0");
    }

    private List<AdDocument> searchIndex(String keyword, String searchtype, String type) {
        List<AdDocument> list = new ArrayList<AdDocument>();

        try {
            ExactFilter filter1 = new ExactFilter("searchtype", searchtype);
            ExactFilter filter2 = new ExactFilter("searchtype", "0");
            Filter searchfilter = new ChainedFilter(new Filter[] { filter1, filter2 },
                    ChainedFilter.OR);
            ExactFilter typefilter = new ExactFilter("adtype", type);
            Filter filter = new ChainedFilter(new Filter[] { searchfilter, typefilter },
                    ChainedFilter.AND);
            Sort sort = new Sort(new SortField[] { new SortField("date", SortField.STRING, false) });
            Query query = parser.parse(keyword);
            TopDocs topDocs = searcher.search(query,10, sort);
            
            ScoreDoc scoreDocs[] = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                AdDocument ad = DocumentParser.convertad(doc);
                list.add(ad);
            }
        } catch (Exception e) {
            log.error("ERR:" + e.getMessage());
            e.printStackTrace();
        }

        try {
            ExactFilter filter1 = new ExactFilter("searchtype", searchtype);
            ExactFilter filter2 = new ExactFilter("searchtype", "0");
            Filter searchfilter = new ChainedFilter(new Filter[] { filter1, filter2 },
                    ChainedFilter.OR);
            ExactFilter typefilter = new ExactFilter("adtype", type);
            Filter filter = new ChainedFilter(new Filter[] { searchfilter, typefilter },
                    ChainedFilter.AND);
            Sort sort = new Sort(new SortField[] { new SortField("date", SortField.STRING, false) });
            Query query = parser.parse("jock");
            TopDocs topDocs = searcher.search(query,10, sort);
            
            ScoreDoc scoreDocs[] = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                AdDocument ad = DocumentParser.convertad(doc);
                list.add(ad);
            }
        } catch (Exception e) {
            log.error("ERR:" + e.getMessage());
            e.printStackTrace();
        }
        log.info("result:" + list.size());
        return list;
    }
}
