package biz.qianyan.search.patent.query.v2;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
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
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.qianyan.search.express.document.ClassResult;
import biz.qianyan.search.express.query.FilterFactory;
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
    private static final Logger log = LoggerFactory.getLogger(PatentSearchImpl.class);

    private DocumentParser docparser;

    private String[] indexdir;

    // private String field;

    private PatentQueryParser parser;
    // private MultiFieldQueryParser multiparser;

    // private Analyzer analyzer;

    private MultiReader reader;

    private MultiSearcher searcher;

    public PatentSearchImpl(String[] indexdir, PatentQueryParser parser, DocumentParser docparser) {
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
            log.error(e.getLocalizedMessage());
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
            SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<B>","</B>");//设定高亮显示的格式，也就是对高亮显示的词组加上前缀后缀  
            Highlighter highlighter = new Highlighter(simpleHtmlFormatter,new QueryScorer(query));  
            highlighter.setTextFragmenter(new SimpleFragmenter(150));//设置每次返回的字符数.想必大家在使用搜索引擎的时候也没有一并把全部数据展示出来吧，当然这里也是设定只展示部分数据  
            int i = 0;
            ScoreDoc scoreDocs[] = hits.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {

                Document doc = searcher.doc(scoreDoc.doc);

                PatentDocument im = DocumentParser.convert(doc);
                try {

                    TokenStream tokenStream = parser.analyzer.tokenStream("",new StringReader(im.getTitle()));  
                    String str = highlighter.getBestFragment(tokenStream, im.getTitle()); 
                    if (str != null) {
                        im.setTitle(str);
                    }
                     tokenStream = parser.analyzer.tokenStream("",new StringReader(doc.get("brief")));  
                    String brief = highlighter.getBestFragment(tokenStream, doc.get("brief")); 
//                    String brief = highlighter.highlight(doc.get("brief"), keyword, true);

                    if (brief != null) {
                        im.setBrief(brief);
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }

                list.add(im);

            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<ClassResult> searchClass(PatentForm s, Navbar page) {
        // TODO Auto-generated method stub
        return null;
    }

}
