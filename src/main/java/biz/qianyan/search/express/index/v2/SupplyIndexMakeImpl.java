/**
 * Specl.com Inc.
 * Copyright (c) 2010-2012 All Rights Reserved.
 */
package biz.qianyan.search.express.index.v2;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.qianyan.search.db.model.VwSearchSupply;
import biz.qianyan.search.db.model.VwSearchSupplyDAO;
import biz.qianyan.search.express.document.DocumentParser;
import biz.qianyan.search.express.index.IndexMaker;
import biz.qianyan.search.util.Config;

/**
 * @author jock
 */
public class SupplyIndexMakeImpl implements IndexMaker {

    private static final Logger log = LoggerFactory.getLogger(IndexMakeImpl.class);

    private Analyzer analyzer;
    private VwSearchSupplyDAO dao;

    private DocumentParser docparser;

    private String file = "MaxSupplyid";

    private String indexdir;

    private IndexWriter writer;

    public SupplyIndexMakeImpl(String indexdir, Analyzer analyzer, VwSearchSupplyDAO dao, DocumentParser docparser,
            String idfile) {
        super();
        this.analyzer = analyzer;
        this.dao = dao;
        this.docparser = docparser;
        this.indexdir = indexdir.trim();
        this.file = idfile.trim();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jock.express.index.IndexMaker#indexBuild(boolean)
     */
    @Override
    public void indexBuild(boolean op) {
        log.info("build index...");
        try {
            log.debug("write index on " + indexdir);
            System.out.println("write index on " + indexdir);
            int i = 0, id = 0;
            id = readId();
            Directory dir = FSDirectory.open(new File(indexdir));

            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            if (id == 0)
                iwc.setOpenMode(OpenMode.CREATE);
            iwc.setRAMBufferSizeMB(512.0);
            writer = new IndexWriter(dir, iwc);
            // 10261759
            // writer.setMaxBufferedDocs(10000);
            // writer.setMaxMergeDocs(20000);
            // writer.setMergeFactor(10000);
            for (int j = 0; j < Config.INDEX; j++) {
                List<VwSearchSupply> list = dao.findAllLt(id);
                if (list.size() == 0)
                    break;
                for (VwSearchSupply record : list) {
                    log.debug("add recordid:" + record.getId());
                    id = record.getId();
                    try {
                        writer.addDocument(DocumentParser.parse(DocumentParser.tranfer(record)));
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                log.info(j+" add 500 records. ");
            }
            writeId(id);

            log.info("add " + i + " records");
            if (op) {
                log.info("optimize index...");
                writer.forceMerge(1);
            }
            writer.close();

            // VwSearchSupply ss = dao.findById(12000001);
            // writer.addDocument(docparser.parse(docparser.tranfer(ss)));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        }

    }

    private int readId() throws Exception {
        int id = 0;
        try {
            RandomAccessFile rf = new RandomAccessFile(file, "r");
            id = rf.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private void writeId(int id) throws Exception {
        try {
            RandomAccessFile rf = new RandomAccessFile(file, "rw");
            rf.setLength(0);
            rf.writeInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
