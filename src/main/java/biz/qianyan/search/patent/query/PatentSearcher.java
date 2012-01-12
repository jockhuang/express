package biz.qianyan.search.patent.query;

import java.util.List;

import biz.qianyan.search.express.document.ClassResult;
import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.PatentForm;
import biz.qianyan.search.patent.document.PatentDocument;

public interface PatentSearcher {
    public List<PatentDocument> search(PatentForm s, Navbar page);

    public List<ClassResult> searchClass(PatentForm s, Navbar page);
}
