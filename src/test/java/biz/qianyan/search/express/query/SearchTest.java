package biz.qianyan.search.express.query;

import java.io.File;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.CachingCollector;
import org.apache.lucene.search.IndexSearcher;
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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import biz.qianyan.search.express.document.DocumentParser;
import biz.qianyan.search.express.document.ExpressDocument;

public class SearchTest {
    
    /**
     * @param args
     */
    /**
     * @param args
     */
    public static void main(String[] args) {

        // System.exit(0);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Directory directory = FSDirectory.open(new File("/Users/jock/work/express/index/index0"));
            IndexReader reader = IndexReader.open(directory);

            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new IKAnalyzer();
            QueryParser parser = new QueryParser(Version.LUCENE_35, "title",analyzer
                    );

            Query query = parser.parse("电子集团");
            Sort sort = new Sort(new SortField[] { new SortField("createdate", SortField.STRING, true) });
            long begin = System.currentTimeMillis();

            String field = "fullpath";
            int topNGroups = 100;
            int groupOffset = 0;
            int maxDocsPerGroup = 10;
            int withinGroupOffset = 0;

            TermFirstPassGroupingCollector c1 = new TermFirstPassGroupingCollector(field, Sort.RELEVANCE, topNGroups);
            boolean cacheScores = true;
            double maxCacheRAMMB = 4.0;
            CachingCollector cachedCollector = CachingCollector.create(c1, cacheScores, maxCacheRAMMB);
            searcher.search(query, cachedCollector);
            Collection<SearchGroup<String>> topGroups = c1.getTopGroups(groupOffset, true);

            TermSecondPassGroupingCollector c2 = new TermSecondPassGroupingCollector(field, topGroups, Sort.RELEVANCE,
                    Sort.RELEVANCE, maxDocsPerGroup, true, true, true);
            if (cachedCollector.isCached()) {
                // Cache fit within maxCacheRAMMB, so we can replay it:
                cachedCollector.replay(c2);
            } else {
                // Cache was too large; must re-execute query:
                searcher.search(query, c2);
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
                System.out.println(gd.groupValue + ":" + gd.totalHits);
            }
            
            
            long end = System.currentTimeMillis();
            System.out.println("group by time :" + (end - begin) + "ms");

            TopDocs topDocs = searcher.search(query, 50, sort);
            System.out.println(topDocs.totalHits);
            ScoreDoc scoreDocs[] = topDocs.scoreDocs;
            SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<font color=\"red\">","</font>");//设定高亮显示的格式，也就是对高亮显示的词组加上前缀后缀  
            Highlighter highlighter = new Highlighter(simpleHtmlFormatter,new QueryScorer(query));  
            highlighter.setTextFragmenter(new SimpleFragmenter(80));//设置每次返回的字符数.想必大家在使用搜索引擎的时候也没有一并把全部数据展示出来吧，当然这里也是设定只展示部分数据  
            System.out.println("=================================================");
            for (ScoreDoc scoreDoc : scoreDocs) {

                Document doc = searcher.doc(scoreDoc.doc);
                ExpressDocument ad = DocumentParser.convert(doc);
                TokenStream tokenStream = analyzer.tokenStream("",new StringReader(ad.getTitle()));  
                String str = highlighter.getBestFragment(tokenStream, ad.getTitle()); 
                if (str != null) {
                    ad.setTitle(str);
                }
                 tokenStream = analyzer.tokenStream("",new StringReader(doc.get("brief")));  
                String brief = highlighter.getBestFragments(tokenStream, doc.get("brief"),1,"..."); 
//                String brief = highlighter.highlight(doc.get("brief"), keyword, true);

                if (brief != null) {
                    ad.setBrief(brief);
                }
                System.out.println(ad.getBrief());
                

            }

            searcher.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
