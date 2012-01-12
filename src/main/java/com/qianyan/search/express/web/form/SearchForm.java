/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package com.qianyan.search.express.web.form;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.qianyan.search.express.web.util.BizModeSelect;
import com.qianyan.search.express.web.util.DateSelect;
import com.qianyan.search.express.web.util.LabelValueBean;
import com.qianyan.search.express.web.util.YearSelect;

/**
 * MyEclipse Struts Creation date: 01-08-2008 XDoclet definition:
 * 
 * @struts.form name="searchForm"
 */
public class SearchForm extends ActionForm {

	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** class */
	private String c;

	private String capitalCN;

	/** date */
	private int d;

	/** date range select */
	private Vector<LabelValueBean> dateselect = DateSelect.getDates();

	private Vector<LabelValueBean> yearselect = YearSelect.getYears();

	private Vector<LabelValueBean> regselect;

	private Vector<LabelValueBean> provinceselect;

	private Vector<LabelValueBean> citysselect;

	/** encoded query */
	private String eq;

	/** bizmode */
	private int m;

	/** bizmode select */
	private Vector<LabelValueBean> modeselect = BizModeSelect.getModes();

	/** page property */
	private int p;

	private String provinceCN;

	/** query property */
	private String q;

	/** sort flag */
	private int s = 1;

	private String st;
	/** type */
	private int t;

	private int y = 0;

	/** 结果样式 0：正常搜索结果页search.jsp  1：左侧相关搜索left.jsp  2：diy相关搜索diy.jsp */
	private int r;
	
	private boolean pic;

	private int f;
	/** 搜索最新 */
	private int n = 0;
	
	/** 内容字数 */
	private int cn = 20;
	
	/** 结果条数 */
	private int num = 10;
	
	/** 是否显示超链接1：是   0：否 */
	private int l = 1;

	/*
	 * Generated Methods
	 */

	/**
	 * @return the f
	 */
	public int getF() {
		return f;
	}

	public int getCn() {
        return cn;
    }

    public void setCn(int cn) {
        this.cn = cn;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    /**
	 * @param f
	 *            the f to set
	 */
	public void setF(int f) {
		this.f = f;
	}

	public Vector<LabelValueBean> getRegselect() {
		return regselect;
	}

	public void setRegselect(Vector<LabelValueBean> regselect) {
		this.regselect = regselect;
	}

	public boolean isPic() {
		return pic;
	}

	public void setPic(boolean pic) {
		this.pic = pic;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the c
	 */
	public String getC() {
		return c;
	}

	/**
	 * @return the capitalCN
	 */
	public String getCapitalCN() {
		return capitalCN;
	}

	/**
	 * @return the d
	 */
	public int getD() {
		return d;
	}

	/**
	 * @return the dateselect
	 */
	public Vector<LabelValueBean> getDateselect() {
		return dateselect;
	}

	/**
	 * @return the eq
	 */
	public String getEq() {
		return eq;
	}

	/**
	 * @return the m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @return the modeselect
	 */
	public Vector<LabelValueBean> getModeselect() {
		return modeselect;
	}

	/**
	 * @return the p
	 */
	public int getP() {
		return p;
	}

	/**
	 * @return the provinceCN
	 */
	public String getProvinceCN() {
		return provinceCN;
	}

	/**
	 * @return the q
	 */
	public String getQ() {
		return q;
	}

	/**
	 * @return the s
	 */
	public int getS() {
		return s;
	}

	/**
	 * @return the st
	 */
	public String getSt() {
		return st;
	}

	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setT(0);
		setP(1);
		regselect = new Vector<LabelValueBean>();
		regselect.add(new LabelValueBean("工商注册年份", "-1"));
		Calendar cal = Calendar.getInstance();
		int curyear = cal.get(Calendar.YEAR);
		for (int i = curyear; i > 1984; i--) {
			regselect.add(new LabelValueBean(i + "年", i + ""));

		}
		r = 0;
		f = 0;
		n = 0;
		l = 1;
		num = 10;
		pic = false;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}

	/**
	 * @param capitalCN
	 *            the capitalCN to set
	 */
	public void setCapitalCN(String capitalCN) {
		this.capitalCN = capitalCN;
	}

	/**
	 * @param d
	 *            the d to set
	 */
	public void setD(int d) {
		this.d = d;
	}

	/**
	 * @param m
	 *            the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * @param p
	 *            the p to set
	 */
	public void setP(int p) {
		this.p = p;
	}

	/**
	 * @param provinceCN
	 *            the provinceCN to set
	 */
	public void setProvinceCN(String provinceCN) {
		this.provinceCN = provinceCN;
	}

	/**
	 * @param q
	 *            the q to set
	 */
	public void setQ(String q) {
		this.q = q.trim();
		try {
			this.eq = URLEncoder.encode(q, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param s
	 *            the s to set
	 */
	public void setS(int s) {
		if (s > 1 || s < 0)
			s = 0;
		this.s = s;
	}

	private void setSt(int t) {
		switch (t) {
		case 1:
			st = "商机";
			break;
		case 3:
			st = "企业";
			break;
		case 2:
			st = "产品";
			break;
		case 4:
			st = "求购";
			break;
		case 5:
			st = "供应";
			break;
		default:
			st = "全部";
			break;

		}
	}

	/**
	 * @param t
	 *            the t to set
	 */
	public void setT(int t) {
		if (t > 5 || t < 0)
			t = 0;
		setSt(t);

		this.t = t;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the yearselect
	 */
	public Vector<LabelValueBean> getYearselect() {
		return yearselect;
	}

	/**
	 * @return the provinceselect
	 */
	public Vector<LabelValueBean> getProvinceselect() {
		return provinceselect;
	}

	/**
	 * @param provinceselect
	 *            the provinceselect to set
	 */
	public void setProvinceselect(Vector<LabelValueBean> provinceselect) {
		this.provinceselect = provinceselect;
	}

	/**
	 * @return the citysselect
	 */
	public Vector<LabelValueBean> getCitysselect() {
		return citysselect;
	}

	/**
	 * @param citysselect
	 *            the citysselect to set
	 */
	public void setCitysselect(Vector<LabelValueBean> citysselect) {
		this.citysselect = citysselect;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}