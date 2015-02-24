package com.newlecture.jspprj.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ForTag extends TagSupport {
	private int index=0;
	private int count=5;
	private String var=null;
	private String begin=null;
	private String end=null;
	
	
	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int doStartTag() throws JspException {
		if(var!=null)
			pageContext.setAttribute(var, index);
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		index++;
		if (var != null)
			pageContext.setAttribute(var, index);
		if (index < count)
			return EVAL_BODY_AGAIN;
		else
			return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		if(var!=null)
			pageContext.removeAttribute(var);
		return EVAL_PAGE;
	}

}
