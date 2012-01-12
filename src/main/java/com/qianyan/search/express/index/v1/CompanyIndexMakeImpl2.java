/**
 * 
 */
package com.qianyan.search.express.index.v1;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.qianyan.search.db.model.VwSearchComs;
import com.qianyan.search.db.model.VwSearchComsDAO;
import com.qianyan.search.express.document.DocumentParser;
import com.qianyan.search.express.index.IndexMaker;

/**
 * @author Jock
 */
public class CompanyIndexMakeImpl2 implements IndexMaker {
    private static final Log log = LogFactory.getLog(CompanyIndexMakeImpl.class);
    private Analyzer analyzer;
    private VwSearchComsDAO dao;

    private DocumentParser docparser;

    private String file = "Companyid";

    private String indexdir;

    private IndexWriter writer;

    public CompanyIndexMakeImpl2(String indexdir, Analyzer analyzer, VwSearchComsDAO dao, DocumentParser docparser,
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
            iwc.setRAMBufferSizeMB(512.0);
            writer = new IndexWriter(dir, iwc);
            // writer.setMaxBufferedDocs(10000);
            // writer.setMaxMergeDocs(20000);

            List<VwSearchComs> list = dao.findAll2(id);
            if (list.size() > 0) {

                for (VwSearchComs record : list) {
                    log.info("add recordid:" + record.getTitle());
                    id = record.getId();
                    try {
                        writer.addDocument(DocumentParser.parse(DocumentParser.tranfer(record)));
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (i >= 500) {
                        break;
                    }

                }
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
            log.error(e.fillInStackTrace());
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
