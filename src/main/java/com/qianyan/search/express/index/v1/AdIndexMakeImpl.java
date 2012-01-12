/**
 * 
 */
package com.qianyan.search.express.index.v1;

import java.io.File;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.qianyan.search.db.model.BusiOrder;
import com.qianyan.search.db.model.BusiOrderDAO;
import com.qianyan.search.db.model.VwSearchComsDAO;
import com.qianyan.search.db.model.VwSearchProduct;
import com.qianyan.search.db.model.VwSearchProductDAO;
import com.qianyan.search.db.model.VwSearchSupply;
import com.qianyan.search.db.model.VwSearchSupplyDAO;
import com.qianyan.search.express.document.AdDocument;
import com.qianyan.search.express.document.DocumentParser;
import com.qianyan.search.express.document.ExpressDocument;
import com.qianyan.search.express.index.IndexMaker;

/**
 * @author Jock
 */
public class AdIndexMakeImpl implements IndexMaker {

    private static final Log   log = LogFactory.getLog(AdIndexMakeImpl.class);

    private Analyzer           analyzer;
    private BusiOrderDAO       dao;
    private VwSearchComsDAO    cdao;
    private VwSearchSupplyDAO  sdao;
    private VwSearchProductDAO pdao;
    private DocumentParser     docparser;

    private String             indexdir;

    private IndexWriter        writer;

    public AdIndexMakeImpl(String indexdir, Analyzer analyzer, BusiOrderDAO dao,
                           DocumentParser docparser) {
        super();
        this.analyzer = new SimpleAnalyzer(Version.LUCENE_35);
        this.dao = dao;
        this.docparser = docparser;
        this.indexdir = indexdir.trim();

    }

    /**
     * @param cdao
     *            the cdao to set
     */
    public void setCdao(VwSearchComsDAO cdao) {
        this.cdao = cdao;
    }

    /**
     * @param sdao
     *            the sdao to set
     */
    public void setSdao(VwSearchSupplyDAO sdao) {
        this.sdao = sdao;
    }

    /**
     * @param pdao
     *            the pdao to set
     */
    public void setPdao(VwSearchProductDAO pdao) {
        this.pdao = pdao;
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
            int i = 0;
            Directory dir = FSDirectory.open(new File(indexdir));
            
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            iwc.setOpenMode(OpenMode.CREATE);
            iwc.setRAMBufferSizeMB(256.0);
            writer = new IndexWriter(dir, iwc);
           
            // writer.setMaxBufferedDocs(10000);
            // writer.setMaxMergeDocs(20000);
            int allcount = dao.count();

            List<BusiOrder> list = dao.findAll();

            for (BusiOrder record : list) {
                log.info("add recordid:" + record.getId());

                try {
                    System.out.println(DocumentParser.tranfer(record) + "===============\n");
                    AdDocument ad = DocumentParser.tranfer(record);
                    if (ad.getType() == 1 && ad.getConfigtype() == 0) {
                        ExpressDocument ed = null;
                        if (ad.getInfotype() == 1) {

                            List<VwSearchSupply> l = sdao.findBySsid(ad.getSsid());
                            if (l.size() > 0) ed = DocumentParser.tranfer(l.get(0));

                        }
                        else if (ad.getInfotype() == 10) {
                            List<VwSearchProduct> l = pdao.findBySsid(ad.getSsid());
                            if (l.size() > 0) ed = DocumentParser.tranfer(l.get(0));
                        }
                        else if (ad.getInfotype() == 20) {
                            if (cdao.findById(ad.getSsid()) != null) {
                                ed = DocumentParser.tranfer(cdao.findById(ad.getSsid()));
                            }
                        }

                        short type = ad.getAdtype();
                        if (ed != null) {

                            BeanUtils.copyProperties(ad, ed);

                            ad.setAdtype(type);
                            log.info("need read infotype=" + ad.getInfotype() + "\n   "
                                    + ad.toString());
                        }
                        else
                            log.error(ad.getSsid() + " not found!");
                    }
                    writer.addDocument(DocumentParser.parse(ad));
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i >= 50000) {
                    break;
                }

            }
            log.info("add " + i + " records");
            writer.forceMerge(1);
            log.info("optimize index...");
            writer.close();

            // VwSearchSupply ss = dao.findById(12000001);
            // writer.addDocument(docparser.parse(docparser.tranfer(ss)));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.fillInStackTrace());
        }

    }

}
