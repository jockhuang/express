/**
 * 
 */
package biz.qianyan.search.express.index;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jock
 */
public class ReleatedKeywordIndex {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("indexContext.xml");

        IndexMaker index = (IndexMaker) ctx.getBean("ReleatedIndexBuild");

        index.indexBuild(true);

    }

}
