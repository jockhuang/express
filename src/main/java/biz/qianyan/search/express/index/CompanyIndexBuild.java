package biz.qianyan.search.express.index;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CompanyIndexBuild {

    public static void main(String[] args) {

        String[] s = new String[2];
        s[0] = "searchContext.xml";
        s[1] = "indexContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(s);

        if (args.length > 0) {
            IndexMaker index = (IndexMaker) ctx.getBean("ComIndexBuild2");
            index.indexBuild(true);
        }
        else {
            IndexMaker index = (IndexMaker) ctx.getBean("ComIndexBuild");
            index.indexBuild(true);
        }

    }
}
