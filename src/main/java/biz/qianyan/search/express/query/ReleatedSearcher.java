/**
 * 
 */
package biz.qianyan.search.express.query;

import java.util.List;

import biz.qianyan.search.express.document.ReleatedKeyword;
import biz.qianyan.search.express.web.Navbar;

/**
 * @author Jock
 */
public interface ReleatedSearcher {
    public List<ReleatedKeyword> search(String s, Navbar page);
}
