package biz.qianyan.search.express.query.v2;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.search.CachingCollector;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.SearchGroup;
import org.apache.lucene.search.grouping.TermFirstPassGroupingCollector;
import org.apache.lucene.search.grouping.TermSecondPassGroupingCollector;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;

import biz.qianyan.search.express.document.ClassResult;
import biz.qianyan.search.express.document.DocumentParser;
import biz.qianyan.search.express.document.ExpressDocument;
import biz.qianyan.search.express.query.ExpressQueryParser;
import biz.qianyan.search.express.query.ExpressSearcher;
import biz.qianyan.search.express.query.FilterFactory;
import biz.qianyan.search.express.query.KeywordFormat;
import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.SearchForm;

/**
 * @author Jock
 */
public class ExpressSearcherImpl implements ExpressSearcher {

    private static final Log log = LogFactory.getLog(ExpressSearcherImpl.class);

    private DocumentParser docparser;

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
    public ExpressSearcherImpl(String[] indexdir, ExpressQueryParser parser, DocumentParser docparser) {

        this.parser = parser;

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
            sort = new Sort( new SortField("createdate", SortField.LONG, true) );
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
            SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<B>","</B>");//设定高亮显示的格式，也就是对高亮显示的词组加上前缀后缀  
            Highlighter highlighter = new Highlighter(simpleHtmlFormatter,new QueryScorer(query));  
            highlighter.setTextFragmenter(new SimpleFragmenter(150));//设置每次返回的字符数.想必大家在使用搜索引擎的时候也没有一并把全部数据展示出来吧，当然这里也是设定只展示部分数据  
            int size = list.size();
            if (needreload || page.getCurrpage() > 1) {
                ScoreDoc scoreDocs[] = hits.scoreDocs;
                for (ScoreDoc scoreDoc : scoreDocs) {
                    Document doc = searcher.doc(scoreDoc.doc);

                    ExpressDocument im = DocumentParser.convert(doc);
                    try {
                        if (s.getR() == 0) {
                            
                            
                            TokenStream tokenStream = parser.analyzer.tokenStream("",new StringReader(im.getTitle()));  
                            String str = highlighter.getBestFragment(tokenStream, im.getTitle()); 
                            if (str != null) {
                                im.setTitle(str);
                            }
                             tokenStream = parser.analyzer.tokenStream("",new StringReader(doc.get("brief")));  
                            String brief = highlighter.getBestFragment(tokenStream, doc.get("brief")); 
//                            String brief = highlighter.highlight(doc.get("brief"), keyword, true);

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
        String keyword = s.getQ().trim();

        keyword = KeywordFormat.convertKeyword(keyword);
        if (keyword.equals("")) {
            return list;
        }
        Query query = null;
        try {
            if (1 == s.getF()) {
                query = parser.titleparse(keyword);
            } else {
                query = parser.parse(keyword);
            }
            

            Filter filter1 = FilterFactory.getFilter(s);
            Filter filter2 = FilterFactory.getCompanyFilter(s);
            Filter filter = null;
            if(s.getT()==3)
                filter = filter2;
            else
                filter = filter1;
            String field = "fullpath";
            int topNGroups = 100;
            int groupOffset = 0;
            int maxDocsPerGroup = 10;
            int withinGroupOffset = 0;

            TermFirstPassGroupingCollector c1 = new TermFirstPassGroupingCollector(field, Sort.RELEVANCE, topNGroups);
            boolean cacheScores = true;
            double maxCacheRAMMB = 4.0;
            CachingCollector cachedCollector = CachingCollector.create(c1, cacheScores, maxCacheRAMMB);
            searcher.search(query, filter,cachedCollector);
            Collection<SearchGroup<String>> topGroups = c1.getTopGroups(groupOffset, true);

            TermSecondPassGroupingCollector c2 = new TermSecondPassGroupingCollector(field, topGroups, Sort.RELEVANCE,
                    Sort.RELEVANCE, maxDocsPerGroup, true, true, true);
            if (cachedCollector.isCached()) {
                // Cache fit within maxCacheRAMMB, so we can replay it:
                cachedCollector.replay(c2);
            } else {
                // Cache was too large; must re-execute query:
                searcher.search(query, filter,c2);
            }

            TopGroups<String> tg = c2.getTopGroups(withinGroupOffset);
            GroupDocs<String>[] gds = tg.groups;
            System.out.println("groups:"+gds.length);
            class GroupComparator implements Comparator<GroupDocs>{
                public int compare(GroupDocs pFirst, GroupDocs pSecond) {
                    if(pFirst.totalHits>pSecond.totalHits)
                        return -1;
                    else if(pFirst.totalHits<pSecond.totalHits)
                        return 1;
                    else
                        return 0;
                }
            }
            Arrays.sort(gds,new GroupComparator());
            for (GroupDocs<String> gd : gds) {
                ClassResult cr = new ClassResult(gd.groupValue, gd.totalHits);
                list.add(cr);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        return list;
    }

}
