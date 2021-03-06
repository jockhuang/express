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

import biz.qianyan.search.express.query.ExpressSearcher;
import biz.qianyan.search.express.web.form.RedoForm;

/**
 * MyEclipse Struts Creation date: 02-10-2008 XDoclet definition:
 * 
 * @struts.action path="/redo" name="redoForm" input="/redo.jsp" scope="request" validate="true"
 * @struts.action-forward name="RESULT" path="/redo.jsp"
 */
public class RedoAction extends Action {

    private ExpressSearcher searcher;

    /**
     * Method execute
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        RedoForm redoForm = (RedoForm) form;// TODO Auto-generated method stub
        if ("jock".equals(redoForm.getName())) {
            searcher.reOpen();
            redoForm.setMsg("SUCCESS!");
        } else {
            redoForm.setMsg("FAILURE!");
        }
        return mapping.findForward("RESULT");
    }

    /*
     * Generated Methods
     */

    /**
     * @param searcher the searcher to set
     */
    public final void setSearcher(ExpressSearcher searcher) {
        this.searcher = searcher;
    }
}
