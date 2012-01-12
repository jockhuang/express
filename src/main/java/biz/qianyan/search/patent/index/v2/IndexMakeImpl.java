package biz.qianyan.search.patent.index.v2;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import biz.qianyan.search.db.model.VwSearchPatent;
import biz.qianyan.search.db.model.VwSearchPatentDAO;
import biz.qianyan.search.patent.document.DocumentParser;
import biz.qianyan.search.patent.index.IndexMake;

/**
 * 
 * @author Jock
 */
public class IndexMakeImpl implements IndexMake {
    private static final Log log = LogFactory.getLog(IndexMakeImpl.class);

    private Analyzer analyzer;
    private VwSearchPatentDAO dao;

    private DocumentParser docparser;

    private String file = "Patentid";

    private String indexdir;

    private IndexWriter writer;

    public IndexMakeImpl(String indexdir, Analyzer analyzer, VwSearchPatentDAO dao, DocumentParser docparser,
            String idfile) {
        super();
        this.analyzer = analyzer;
        this.dao = dao;
        this.docparser = docparser;
        this.indexdir = indexdir.trim();
        this.file = idfile.trim();

    }

    @Override
    public void indexBuild(boolean op) {
        log.info("build index...");
        try {
            log.debug("write index on " + indexdir);
            System.out.println("write index on " + indexdir);
            int i = 0, id = 0;
            id = readId();
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            iwc.setRAMBufferSizeMB(512.0);

            // writer = new IndexWriter(indexdir, analyzer, true);
            writer = new IndexWriter(FSDirectory.open(new File(indexdir)), iwc);

            // writer.setMaxBufferedDocs(10000);
            // writer.setMaxMergeDocs(20000);
            // writer.setMergeFactor(10000);
            int allcount = dao.count();

            while (i < allcount) {
                List<VwSearchPatent> list = dao.findAll(id);
                if (list.size() == 0) {
                    break;
                }
                for (VwSearchPatent record : list) {
                    log.info("add recordid:" + record.getId());
                    id = record.getId();
                    try {
                        writer.addDocument(DocumentParser.parser(DocumentParser.transfer(record)));
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (i >= 200000) {
                        break;
                    }

                }
                if (i >= 200000) {
                    break;
                }
            }

            writeId(id);

            log.info("add " + i + " records");
            if (op) {
                writer.forceMerge(1);
                log.info("optimize index...");

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
            rf.writeInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
