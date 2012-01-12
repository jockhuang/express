/**
 * 
 */
package com.qianyan.search.express.index;

import java.io.RandomAccessFile;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qianyan.search.db.model.ForbidKeywords;
import com.qianyan.search.db.model.ForbidKeywordsDAO;

/**
 * @author Jock
 */
public class BlackWords {

    private ForbidKeywordsDAO dao;
    private static String     file = "/data/search/WEB-INF/blackword.txt";

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(ForbidKeywordsDAO dao) {
        this.dao = dao;
    }

    public void initBlackWords() {
        List<ForbidKeywords> list = dao.findAll();
        try {
            RandomAccessFile re = new RandomAccessFile(file, "rw");
            re.setLength(0);

            for (ForbidKeywords fk : list) {
                re.write(fk.getKeyword().getBytes());
                re.writeBytes("\n");
            }
            re.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] s = new String[2];
        s[0] = "searchContext.xml";
        s[1] = "indexContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(s);
        BlackWords rf = (BlackWords) ctx.getBean("BlackWords");
        rf.initBlackWords();

    }

}