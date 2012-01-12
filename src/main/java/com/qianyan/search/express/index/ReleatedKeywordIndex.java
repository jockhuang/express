/**
 * 
 */
package com.qianyan.search.express.index;

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
        String[] s = new String[2];
        s[0] = "searchContext.xml";
        s[1] = "indexContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(s);

        IndexMaker index = (IndexMaker) ctx.getBean("ReleatedIndexBuild");

        index.indexBuild(true);

    }

}
