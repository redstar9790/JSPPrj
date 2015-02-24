
<%@page import="com.newlecture.jspprj.model.NoticeFile"%>
<%@page import="com.newlecture.jspprj.dao.jdbc.JdbcNoticeFileDao"%>
<%@page import="com.newlecture.jspprj.dao.NoticeFileDao"%>
<%@page import="com.newlecture.jspprj.dao.jdbc.JdbcNoticeDao"%>
<%@page import="com.newlecture.jspprj.dao.NoticeDao"%>
<%@page import="com.newlecture.jspprj.model.Notice"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
ServletContext ctx = request.getServletContext();
String path = ctx.getRealPath("/customer/upload");
out.println(path + "<br />");

MultipartRequest req = new MultipartRequest(request
               , path
               , 1024*1024*10
               , "UTF-8"
               , new DefaultFileRenamePolicy());

String title = request.getParameter("title");
String fileName = request.getParameter("file");
String content = request.getParameter("content");

Notice notice = new Notice();
notice.setTitle(title);
notice.setWriter("jun");
notice.setContent(content);

NoticeDao noticeDao = new JdbcNoticeDao();
noticeDao.insert(notice);

if(req.getFile("file") != null)
{
	String noticeCode = noticeDao.lastCode();

	NoticeFile noticeFile = new NoticeFile();
	noticeFile.setSrc(fileName);
	noticeFile.setDescription("");
	noticeFile.setNoticeCode(noticeCode);

	NoticeFileDao fileDao = new JdbcNoticeFileDao();
	fileDao.insert(noticeFile);	
}


response.sendRedirect("notice.jsp");

%>