/**
 * 
 */
package com.qianyan.search.express.query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
/**
 * @author Jock
 */
public class ExpressQueryParser{
    private MultiFieldQueryParser parser;
    private MultiFieldQueryParser titleparser;
    public ExpressQueryParser(Analyzer analyzer) {

        parser = new MultiFieldQueryParser(Version.LUCENE_35,new String[] { "url", "itemurl", "title", "brief", "content", "keywords", "itemname",
                "contact", "qqcode", "msncode", "comurl", "email", "tel", "fax", "mobile", "zip",
                "address", "route" }, analyzer);
        parser.setDefaultOperator(Operator.AND);
        
        titleparser = new MultiFieldQueryParser(Version.LUCENE_35,new String[] {  "title", "itemname" }, analyzer);
        titleparser.setDefaultOperator(Operator.AND);
    }
    public Query parse(String keyword) throws Exception {
        return parser.parse(keyword);
    }

    public Query titleparse(String keyword) throws Exception {
        return titleparser.parse(keyword);
    }
    
}
