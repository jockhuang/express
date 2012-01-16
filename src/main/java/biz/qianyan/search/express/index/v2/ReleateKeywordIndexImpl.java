/**
 * Specl.com Inc.
 * Copyright (c) 2010-2012 All Rights Reserved.
 */
package biz.qianyan.search.express.index.v2;

import java.io.File;
import java.io.RandomAccessFile;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.qianyan.search.express.document.DocumentParser;
import biz.qianyan.search.express.document.ReleatedKeyword;
import biz.qianyan.search.express.index.IndexMaker;

/**
 * @author Jock
 */
public class ReleateKeywordIndexImpl implements IndexMaker {

    private static final Logger log = LoggerFactory.getLogger(ReleateKeywordIndexImpl.class);
    private String indexdir;

    private IndexWriter writer;
    private Analyzer analyzer;
    private String filename;

    public ReleateKeywordIndexImpl(String indexdir, Analyzer analyzer, String filename) {
        this.analyzer = analyzer;

        this.indexdir = indexdir.trim();
        this.filename = filename.trim();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qianyan.search.express.index.IndexMaker#indexBuild(boolean)
     */
    @Override
    public void indexBuild(boolean op) {
        log.info("build index...");
        try {
            log.debug("write index on " + indexdir);
            System.out.println("write index on " + indexdir);
            Directory dir = FSDirectory.open(new File(indexdir));

            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            iwc.setOpenMode(OpenMode.CREATE);
            iwc.setRAMBufferSizeMB(512.0);
            writer = new IndexWriter(dir, iwc);
            RandomAccessFile rf = new RandomAccessFile(filename, "r");
            String str = null;
            int i = 0;
            while ((str = rf.readLine()) != null) {
                str = new String(str.trim().getBytes("iso8859-1"), "GBK");
                ReleatedKeyword rk = new ReleatedKeyword(str);
                writer.addDocument(DocumentParser.parse(rk));
                log.info("add:" + str);
                i++;
            }

            log.info("add " + i + " records");
            if (op) {
                log.info("optimize index...");
                writer.forceMerge(1);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
