package com.qianyan.search.zhuanli.query;

import java.util.List;

import com.qianyan.search.express.document.ClassResult;
import com.qianyan.search.express.web.Navbar;
import com.qianyan.search.express.web.form.PatentForm;
import com.qianyan.search.zhuanli.document.PatentDocument;

public interface PatentSearcher {
    public List<PatentDocument> search(PatentForm s, Navbar page);

    public List<ClassResult> searchClass(PatentForm s, Navbar page);
}
