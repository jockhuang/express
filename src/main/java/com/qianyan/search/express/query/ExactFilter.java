/**
 * Qianyan.biz Inc.
 * Copyright (c) 2010-2012 All Rights Reserved.
 */
package com.qianyan.search.express.query;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.Filter;
import org.apache.lucene.util.FixedBitSet;

/**
 * 
 * @author jock
 */
public class ExactFilter extends Filter {

    Term term;

    /**
     * 
     */
    public ExactFilter(String field, String value) {
        term = new Term(field, value);
    }

    /**
     * @see org.apache.lucene.search.Filter#getDocIdSet(org.apache.lucene.index.IndexReader)
     */
    @Override
    public DocIdSet getDocIdSet(IndexReader reader) throws IOException {

        FixedBitSet result = new FixedBitSet(reader.maxDoc());
        TermDocs td = reader.termDocs();
        try {

            td.seek(term);
            while (td.next()) {
                result.set(td.doc());
            }

        } finally {
            td.close();
        }
        return result;
    }

}
