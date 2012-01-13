/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package biz.qianyan.search.express.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import biz.qianyan.search.express.query.KeywordFormat;
import biz.qianyan.search.express.web.form.BlackwordForm;

/**
 * MyEclipse Struts Creation date: 03-03-2008 XDoclet definition:
 * 
 * @struts.action path="/blackword" name="blackwordForm" input="/blackword.jsp" scope="request" validate="true"
 */
public class BlackwordAction extends Action {

    /*
     * Generated Methods
     */

    /**
     * Method execute
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        BlackwordForm f = (BlackwordForm) form;
        if (f.getWord() != null && !"".equals(f.getWord())) {
            KeywordFormat.add(f.getWord());
        }
        String s = KeywordFormat.readFile();
        f.setWordlist(s);
        return mapping.findForward("list");
    }
}
