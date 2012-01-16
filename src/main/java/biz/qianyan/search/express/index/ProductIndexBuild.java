package biz.qianyan.search.express.index;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductIndexBuild {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("indexContext.xml");

        IndexMaker index = (IndexMaker) ctx.getBean("ProductIndexBuild");
        if (args.length > 0) {
            index.indexBuild(true);
        } else {
            index.indexBuild(false);
        }

    }
}
