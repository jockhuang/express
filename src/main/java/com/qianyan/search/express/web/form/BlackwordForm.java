/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package com.qianyan.search.express.web.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 03-03-2008 XDoclet definition:
 * 
 * @struts.form name="blackwordForm"
 */
public class BlackwordForm extends ActionForm {

    /*
     * Generated fields
     */

    /** word property */
    private String word;

    /** wordlist property */
    private String wordlist;

    /*
     * Generated Methods
     */

    /**
     * Method validate
     * 
     * @param mapping
     * @param request
     * @return ActionErrors
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Method reset
     * 
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // TODO Auto-generated method stub
    }

    /**
     * Returns the word.
     * 
     * @return String
     */
    public String getWord() {
        return word;
    }

    /**
     * Set the word.
     * 
     * @param word
     *            The word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Returns the wordlist.
     * 
     * @return String
     */
    public String getWordlist() {
        return wordlist;
    }

    /**
     * Set the wordlist.
     * 
     * @param wordlist
     *            The wordlist to set
     */
    public void setWordlist(String wordlist) {
        this.wordlist = wordlist;
    }
}