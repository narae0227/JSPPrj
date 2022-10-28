package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	
	// 제공할 서비스 이기 때문에 public
	public List<Notice> getNoticeList(){
		System.out.println("@@@@@@@@@");
		
		// 3개의 인자값을 가진 곳에서 처리해야하나, 인자값이 없으므로, 기본값으로 셋팅해서 호출해 준다.
		return getNoticeList("TITLE", "", 1);
	}
	
	public List<Notice> getNoticeList(int page){
		
		// page 값만 가지고 있으므로, 나머지는 기본값으로 셋팅하고, page 만 인계받아서 가지고 호출한다.
		return getNoticeList("TITLE", "", page);
	}
	
	public List<Notice> getNoticeList(String field, String query, int page){
		
		System.out.println("@@@@@@@@@ getNoticeList3 : "+ field + " / " + query + " / "+ page);
		
		List<Notice> list = new ArrayList<>();
		
		// field = title, content 등 || quewy = 'a,'bb','cc'등
		String sql = "SELECT * FROM(" +
				" SELECT ROWNUM NUM, N.* "  +
				" FROM (SELECT * FROM PRJ_MR.NOTICE WHERE "+ field +" LIKE ? ORDER BY REGDATE DESC) N ) " + 
				" WHERE NUM BETWEEN ? AND ?" ;
		 
		// ? 에 넣을 list의 시작글순서 끝글순서 - 현재 선택한 페이지에서 보일 글목록
		// 1, 11, 21, 31 -> an = 1+(page-1)*10
		// 10, 20, 30, 40 -> page*10
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "PRJ_MR","1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10); // 시작글번호
			st.setInt(3, page*10); // 끝글번호
			
			 
			
			ResultSet rs = st.executeQuery();

			 while(rs.next()) {
				 int id = rs.getInt("id");
				 String title = rs.getString("title"); 
				 Date regdate =  rs.getDate("REGDATE"); 
				 String writerId = rs.getString("WRITER_ID"); 
				 String hit = rs.getString("HIT"); 
				 String file = rs.getString("FILES"); 
				 String content = rs.getString("CONTENT"); 
				 
				 Notice notice = new Notice(id, title, regdate, writerId, hit,  file, content);
				 list.add(notice);
					
			 }
				rs.close();
				st.close();
				con.close();
				
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		

		return list;
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		
		int count = 0;
		
		String sql = "SELECT COUNT(ID) AS COUNT FROM(" +
				"	SELECT ROWNUM NUM, N.* " +
				"	FROM (SELECT * FROM NOTICE WHERE "+ field +" LIKE ? ORDER BY REGDATE DESC) N ) ";
		
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "PRJ_MR","1234");
			PreparedStatement st = con.prepareStatement(sql);  
			
			st.setString(1, "%"+query+"%");
			
			ResultSet rs = st.executeQuery();

			if(rs.next())
				count = rs.getInt("COUNT"); // 소문자로 해도 상관없음. 
			
			rs.close();
			st.close();
			con.close();
				
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return count;
	}
	
	public Notice getNotice(int id) {
		
		Notice notice = null;
		
		String sql = "SELECT * FROM NOTICE WHERE ID = ?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "PRJ_MR","1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id); 
			
			 
			
			ResultSet rs = st.executeQuery();

			 if(rs.next()) { // 값이 존재한다면
				 int nId = rs.getInt("id");
				 String title = rs.getString("title"); 
				 Date regdate =  rs.getDate("REGDATE"); 
				 String writerId = rs.getString("WRITER_ID"); 
				 String hit = rs.getString("HIT"); 
				 String file = rs.getString("FILES"); 
				 String content = rs.getString("CONTENT"); 
				 
				notice = new Notice(nId, title, regdate, writerId, hit,  file, content);
			 }
				rs.close();
				st.close();
				con.close();
				
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		return notice;
	}
	
	public Notice getNextNotice(int id) {
		
		String sql = "SELECT * FROM NOTICE WHERE ID = ("
				+ "	SELECT ID FROM NOTICE "
				+ "		WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID = ?)"
				+ "		AND ROWNUM = 1 )" ;
		
		Notice notice = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "PRJ_MR","1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id); 
			
			 
			
			ResultSet rs = st.executeQuery();

			 if(rs.next()) { // 값이 존재한다면
				 int nId = rs.getInt("id");
				 String title = rs.getString("title"); 
				 Date regdate =  rs.getDate("REGDATE"); 
				 String writerId = rs.getString("WRITER_ID"); 
				 String hit = rs.getString("HIT"); 
				 String file = rs.getString("FILES"); 
				 String content = rs.getString("CONTENT"); 
				 
				notice = new Notice(nId, title, regdate, writerId, hit,  file, content);
			 }
				rs.close();
				st.close();
				con.close();
				
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return notice;
	}
	
	public Notice getPrevNotice(int id) {
		
		String sql = "	SELECT * FROM NOTICE WHERE ID = ("
				+ "	SELECT ID FROM ( SELECT * FROM NOTICE ORDER BY REGDATE DESC) "
				+ "		WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID = ?) "
				+ "		AND ROWNUM = 1 ) ";

		Notice notice = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "PRJ_MR","1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id); 
			
			 
			
			ResultSet rs = st.executeQuery();

			 if(rs.next()) { // 값이 존재한다면
				 int nId = rs.getInt("id");
				 String title = rs.getString("title"); 
				 Date regdate =  rs.getDate("REGDATE"); 
				 String writerId = rs.getString("WRITER_ID"); 
				 String hit = rs.getString("HIT"); 
				 String file = rs.getString("FILES"); 
				 String content = rs.getString("CONTENT"); 
				 
				notice = new Notice(nId, title, regdate, writerId, hit,  file, content);
			 }
				rs.close();
				st.close();
				con.close();
				
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return notice;
	}
	

}
