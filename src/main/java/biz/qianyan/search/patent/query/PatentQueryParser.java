/**
 * 
 */
package biz.qianyan.search.patent.query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

/**
 * @author Jock
 */
public class PatentQueryParser {
    private MultiFieldQueryParser parser;
    private MultiFieldQueryParser titleparser;

    public PatentQueryParser(Analyzer analyzer) {

        parser = new MultiFieldQueryParser(Version.LUCENE_35,new String[] { "applyno", "title", "brief", "proposer",
                "originator", "agent", "agentorg", "country", "address" }, analyzer);
        parser.setDefaultOperator(Operator.AND);

        titleparser = new MultiFieldQueryParser(Version.LUCENE_35,new String[] {"applyno", "proposer","title" }, analyzer);
        titleparser.setDefaultOperator(Operator.AND);
    }

    public Query parse(String keyword) throws Exception {
        return parser.parse(keyword);
    }

    public Query titleparse(String keyword) throws Exception {
        return titleparser.parse(keyword);
    }
}
