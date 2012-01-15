package biz.qianyan.search.patent.index;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IndexBuild {
    public static void main(String[] args) {

        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("indexContext.xml");

        IndexMake index = (IndexMake) ctx.getBean("PatentIndexBuild");
        if (args.length > 0) {
            index.indexBuild(true);
        } else {
            index.indexBuild(false);
        }

    }
}
