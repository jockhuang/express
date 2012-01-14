/**
 * 
 */
package biz.qianyan.search.express.index;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jock
 */
public class AdIndexBuild {

    public static void main(String[] args) {

        String[] s = new String[2];
        s[0] = "searchContext.xml";
        s[1] = "indexContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext("indexContext.xml");

        IndexMaker index = (IndexMaker) ctx.getBean("AdIndexBuild");
        if (args.length > 0) {
            index.indexBuild(true);
        } else {
            index.indexBuild(false);
        }

    }
}
