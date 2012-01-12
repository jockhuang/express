/**
 * 
 */
package com.qianyan.search.express.query;

import java.util.List;

import com.qianyan.search.express.document.ClassResult;
import com.qianyan.search.express.document.ExpressDocument;
import com.qianyan.search.express.web.Navbar;
import com.qianyan.search.express.web.form.SearchForm;

/**
 * @author Jock
 */
public interface ExpressSearcher {

    public void reOpen();

    public List<ExpressDocument> search(SearchForm s, Navbar page);

    public List<ClassResult> searchClass(SearchForm s, Navbar page);
}
