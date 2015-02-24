package com.newlecture.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.jspprj.dao.NoticeDao;
import com.newlecture.jspprj.model.Notice;

public class JdbcNoticeDao implements NoticeDao {

   /*
    * String url = "jdbc:oracle:thin:@win.newlecture.com:1521:orcl"; String sql
    * = "SELECT * FROM NOTICES  WHERE CODE = '" + _code + "'";
    * 
    * Class.forName("oracle.jdbc.driver.OracleDriver");
    * 
    * Connection con = DriverManager.getConnection(url, "HR", "class6");
    * Statement st = con.createStatement(); ResultSet rs =
    * st.executeQuery(sql);
    * 
    * rs.next();
    * 
    * Notice n = new Notice();
    * 
    * n.setCode(rs.getString("code")); n.setTitle(rs.getString("title"));
    * n.setRegdate(rs.getDate("regdate")); n.setWriter(rs.getString("writer"));
    * n.setHit(rs.getInt("hit")); n.setContent(rs.getString("content"));
    * rs.close(); st.close(); con.close();
    */

   @Override
   public Notice getNotice(String code) {
      String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
      String sql = "SELECT * FROM NOTICES  WHERE CODE = '" + code + "'";

      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection con = DriverManager.getConnection(url, "sist", "newlec");
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql);

         rs.next();

         Notice n = new Notice();

         n.setCode(rs.getString("code"));
         n.setTitle(rs.getString("title"));
         n.setRegdate(rs.getDate("regdate"));
         n.setWriter(rs.getString("writer"));
         n.setHit(rs.getInt("hit"));
         n.setContent(rs.getString("content"));
         rs.close();
         st.close();
         con.close();

         return n;
      } catch (ClassNotFoundException | SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return null;
   }

   @Override
   public List<Notice> getNotices(int page, String query, String field) {

      int start = 1 + 20 * (page - 1);
      int end = 20 + 20 * (page - 1);
      
      /*String sqlCode = "SELECT * FROM " + "(SELECT ROWNUM NUM, N.* FROM "
               + "(SELECT * FROM NOTICES WHERE " + field
               + " like ? ORDER BY REGDATE DESC) N) "
               + "WHERE NUM BETWEEN ? AND ?";*/
      /*String sql = "SELECT * FROM NOTICES";*/
      String sql = "SELECT * FROM ("
      		+ "SELECT ("
      		+ "ROW_NUMBER() OVER (ORDER BY REGDATE DESC)"
      		+ ") NUM, Notices.* FROM NOTICES WHERE "+field+" LIKE ? ) N "
      		+ "WHERE N.NUM BETWEEN ? AND ?";
      /*String url = "jdbc:oracle:thin:@win.newlecture.com:1521:orcl";*/
      String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
      
      
      System.out.println(sql);
      
      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

         Connection con = DriverManager.getConnection(url, "sist", "newlec");
         PreparedStatement st = con.prepareStatement(sql);

         st.setString(1, "%" + query + "%");
         st.setInt(2, start);
         st.setInt(3, end);
         
         ResultSet rs = st.executeQuery();
         List<Notice> list = new ArrayList<Notice>();

         while (rs.next())
         {
            Notice n = new Notice();
   
            n.setCode(rs.getString("code"));
            n.setTitle(rs.getString("title"));
            n.setRegdate(rs.getDate("regdate"));
            n.setWriter(rs.getString("writer"));
            n.setHit(rs.getInt("hit"));
            n.setContent(rs.getString("content"));
            
             list.add(n);
         }
         
         rs.close();
         st.close();
         con.close();
         
         return list;
         
      } catch (ClassNotFoundException | SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return null;
   }

   @Override
   public List<Notice> getNotices(int page, String quert) {
      
      return getNotices(page,quert,"TITLE");
   }
   
   @Override
   public List<Notice> getNotices(int page) {
      // TODO Auto-generated method stub
      return getNotices(page,"");
   }

   @Override
   public int insert(Notice notice) {
      String sqlCode = "SELECT ISNULL(MAX(CAST(CODE AS INT)),0)+1 CODE FROM NOTICES";
      String sql = "INSERT INTO NOTICES(CODE,TITLE,WRITER,CONTENT,REGDATE,HIT) VALUES(?,?,?,?,SYSDATE,0)";
      String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
      System.out.println(sqlCode);
      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection con = DriverManager.getConnection(url, "sist", "newlec");

         Statement stCon = con.createStatement();
         ResultSet rs = stCon.executeQuery(sqlCode);

         rs.next();

         String code = rs.getString("CODE");

         rs.close();
         stCon.close();

         PreparedStatement st = con.prepareStatement(sql);

         st.setString(1, code);
         st.setString(2, notice.getTitle());
         st.setString(3, notice.getWriter());
         st.setString(4, notice.getContent());

         int result = st.executeUpdate();

         st.close();
         con.close();

         return result;
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return 0;
   }

   @Override
   public int update(Notice notice) {

      String sql = "UPDATE NOTICES SET TITLE=?, CONTENT=?,  WHERE CODE=?";
      String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection con = DriverManager.getConnection(url, "sist", "newlec");
         PreparedStatement st = con.prepareStatement(sql);

         st.setString(1, notice.getTitle());
         st.setString(2, notice.getContent());
         st.setString(3, notice.getCode());
         int result = st.executeUpdate();

         st.close();
         con.close();

         return result;
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return 0;

   }

   @Override
   public int delete(String code) {

      String sql = "DELETE NOTICES WHERE CODE=?";
      String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection con = DriverManager.getConnection(url, "sist", "newlec");
         PreparedStatement st = con.prepareStatement(sql);

         st.setString(1, code);

         int result = st.executeUpdate();

         st.close();
         con.close();

         return result;
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return 0;
   }

   @Override
   public int getSize(String query, String field) {
	  String sql = "SELECT COUNT(*)  CNT FROM NOTICES  WHERE  " + field + " like ? ";
      String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
      
      
      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection con = DriverManager.getConnection(url, "sist", "newlec");
         PreparedStatement st = con.prepareStatement(sql);
         st.setString(1, "%" + query + "%");
         
         ResultSet rs = st.executeQuery();
         
         rs.next();

         int count = rs.getInt("CNT");
         
         rs.close();
         st.close();
         con.close();

         return count;
         
      } catch (ClassNotFoundException | SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return 0;
   }
   

	@Override
	public int getSize(String query) {
		// TODO Auto-generated method stub
		return getSize(query, "TITLE");
	}

	@Override
	public String lastCode() {
		String sql = "SELECT ISNULL(MAX(CAST(CODE AS INT)),0) Code FROM Notices";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
			
			String code = rs.getString("Code");
			
			st.close();
			con.close();

			return code;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public Notice prevNotice(String curCode) {
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		/*String sql = "SELECT * FROM NOTICES  WHERE CODE = '" + code + "'";*/
		String sql = "SELECT * FROM NOTICES WHERE REGDATE > ("
				+ "SELECT REGDATE FROM NOTICES WHERE CODE=?"
						+ ") ORDER BY REGDATE ASC";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, curCode);
			
			ResultSet rs = st.executeQuery();
			rs.next();

			Notice n = new Notice();

			n.setCode(rs.getString("code"));
			n.setTitle(rs.getString("title"));
			n.setRegdate(rs.getDate("regdate"));
			n.setWriter(rs.getString("writer"));
			n.setHit(rs.getInt("hit"));
			
			rs.close();
			st.close();
			con.close();

			return n;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Notice nextNotice(String curCode) {
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
	      String sql = "SELECT * FROM NOTICES WHERE REGDATE < ("
	      		+ "SELECT REGDATE FROM NOTICES WHERE CODE=?"
	      				+ ") ORDER BY REGDATE DESC";

	      try {
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         Connection con = DriverManager.getConnection(url, "sist", "newlec");
	         PreparedStatement st = con.prepareStatement(sql);
	         ResultSet rs = st.executeQuery();
	         st.setString(1, curCode);
	         rs.next();

	         Notice n = new Notice();

	         n.setCode(rs.getString("code"));
	         n.setTitle(rs.getString("title"));
	         n.setRegdate(rs.getDate("regdate"));
	         n.setWriter(rs.getString("writer"));
	         n.setHit(rs.getInt("hit"));
	         
	         rs.close();
	         st.close();
	         con.close();

	         return n;
	      } catch (ClassNotFoundException | SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	      return null;
	}

}