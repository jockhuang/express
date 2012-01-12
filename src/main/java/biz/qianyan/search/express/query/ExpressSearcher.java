/**
 * 
 */
package biz.qianyan.search.express.query;

import java.util.List;

import biz.qianyan.search.express.document.ClassResult;
import biz.qianyan.search.express.document.ExpressDocument;
import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.SearchForm;

/**
 * @author Jock
 */
public interface ExpressSearcher {

    public void reOpen();

    public List<ExpressDocument> search(SearchForm s, Navbar page);

    public List<ClassResult> searchClass(SearchForm s, Navbar page);
}
