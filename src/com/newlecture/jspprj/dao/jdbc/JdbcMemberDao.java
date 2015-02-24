package com.newlecture.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.newlecture.jspprj.dao.MemberDao;
import com.newlecture.jspprj.model.Member;

public class JdbcMemberDao implements MemberDao{

	@Override
	public Member getMember(String uid) {
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		String sql = "SELECT * FROM Members WHERE Mid=?";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uid);
			ResultSet rs = st.executeQuery();

			rs.next();

			Member n = new Member();
			n.setUid(rs.getString("uid"));
			n.setPwd(rs.getString("pwd"));
			n.setName(rs.getString("name"));

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
