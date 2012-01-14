package biz.qianyan.search.express.index;

import java.io.File;
import java.io.RandomAccessFile;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IndexDelete {
    private String[] indexdir;
    private Analyzer analyzer;
    private String idfiles;

    public void setIdfiles(String idfiles) {
        this.idfiles = idfiles;
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
        RandomAccessFile rf = new RandomAccessFile(idfiles, "r");
        String str = null;

        while ((str = rf.readLine()) != null) {
            Term term = new Term("id", str);

            for (int i = 0; i < reader.length; i++) {
                reader[i].deleteDocuments(term);

            }

        }
        rf.close();
        for (int i = 0; i < indexdir.length; i++) {
            reader[i].close();
        }
    }

    public void setIndexdir(String[] indexdir) {
        this.indexdir = indexdir;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("User company,supply,product parameters");
            return;
        }
        String[] s = new String[2];
        s[0] = "searchContext.xml";
        s[1] = "indexContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext("indexContext.xml");
        if ("company".equals(args[0])) {
            IndexDelete im = (IndexDelete) ctx.getBean("CompanyFileDelete");
            try {
                im.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("supply".equals(args[0])) {
            IndexDelete im = (IndexDelete) ctx.getBean("SupplyFileDelete");
            try {
                im.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("product".equals(args[0])) {
            IndexDelete im = (IndexDelete) ctx.getBean("ProductFileDelete");
            try {
                im.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
