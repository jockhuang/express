package com.qianyan.search.zhuanli.index;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qianyan.search.zhuanli.index.IndexMake;

public class IndexBuild {
    public static void main(String[] args) {

        String[] s = new String[2];
        s[0] = "searchContext.xml";
        s[1] = "indexContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(s);

        IndexMake index = (IndexMake) ctx.getBean("PatentIndexBuild");
        if (args.length > 0) {
            index.indexBuild(true);
        }
        else {
            index.indexBuild(false);
        }

    }
}