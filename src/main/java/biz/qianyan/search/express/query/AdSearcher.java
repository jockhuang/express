/**
 * 
 */
package biz.qianyan.search.express.query;

import java.util.List;

import biz.qianyan.search.express.document.AdDocument;
import biz.qianyan.search.express.web.Navbar;
import biz.qianyan.search.express.web.form.SearchForm;

/**
 * @author Jock
 */
public interface AdSearcher {
    public List<AdDocument> search(SearchForm s, Navbar page);

    public List<AdDocument> searchPic(SearchForm s, Navbar page);

    public List<AdDocument> searchText(SearchForm s, Navbar page);
}
