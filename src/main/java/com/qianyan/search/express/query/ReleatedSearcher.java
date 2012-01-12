/**
 * 
 */
package com.qianyan.search.express.query;

import java.util.List;

import com.qianyan.search.express.document.ReleatedKeyword;
import com.qianyan.search.express.web.Navbar;
import com.qianyan.search.express.web.form.SearchForm;

/**
 * @author Jock
 */
public interface ReleatedSearcher {
    public List<ReleatedKeyword> search(String s, Navbar page);
}
