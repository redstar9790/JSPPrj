package com.newlecture.jspprj.dao;

import java.util.List;

import com.newlecture.jspprj.model.Notice;

public interface NoticeDao {
	public Notice getNotice(String code);
	public List<Notice> getNotices(int page, String query, String field);
	public List<Notice> getNotices(int page, String query);
	public List<Notice> getNotices(int page);
	public int insert(Notice notice);
	public int update(Notice notice);
	public int delete(String code);
	public int getSize(String query, String field);
	public int getSize(String query);
	public String lastCode();
	public Notice prevNotice(String curCode);
	public Notice nextNotice(String curCode);
}