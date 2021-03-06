package biz.qianyan.search.express.index;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import biz.qianyan.search.db.model.DeleteItem;
import biz.qianyan.search.db.model.DeleteItemDAO;

public class IndexModify {
    private String[] indexdir;
    private Analyzer analyzer;
    private DeleteItemDAO dao;

    public void setDao(DeleteItemDAO dao) {
        this.dao = dao;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void delete() throws Exception {
        IndexWriter[] reader = new IndexWriter[indexdir.length];
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, analyzer);
        iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
        iwc.setRAMBufferSizeMB(256.0);
        for (int i = 0; i < indexdir.length; i++) {
            reader[i] = new IndexWriter(FSDirectory.open(new File(indexdir[i])), iwc);
        }
        long id = readId(dao.getClass().getName());
        List<DeleteItem> l = dao.findAll(id);
        do {

            for (DeleteItem item : l) {
                Term term = new Term("id", item.getId().toString());

                for (int i = 0; i < reader.length; i++) {
                    reader[i].deleteDocuments(term);

                }
                id = item.getIdid().intValue();

            }
            l = dao.findAll(id);
        } while (l.size() > 0);
        writeId(dao.getClass().getName(), id);
        for (int i = 0; i < indexdir.length; i++) {
            reader[i].close();
        }
    }

    public void setIndexdir(String[] indexdir) {
        this.indexdir = indexdir;
    }

    private long readId(String file) throws Exception {
        long id = 0;
        try {
            RandomAccessFile rf = new RandomAccessFile(file, "r");
            id = rf.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private void writeId(String file, long id) throws Exception {
        try {
            RandomAccessFile rf = new RandomAccessFile(file, "rw");
            rf.setLength(0);
            rf.writeLong(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("User company,supply,product parameters");
            return;
        }
       
        ApplicationContext ctx = new ClassPathXmlApplicationContext("indexContext.xml");
        if ("company".equals(args[0])) {
            IndexModify im = (IndexModify) ctx.getBean("CompanyDelete");
            try {
                im.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("supply".equals(args[0])) {
            IndexModify im = (IndexModify) ctx.getBean("SupplyDelete");
            try {
                im.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("product".equals(args[0])) {
            IndexModify im = (IndexModify) ctx.getBean("ProductDelete");
            try {
                im.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
