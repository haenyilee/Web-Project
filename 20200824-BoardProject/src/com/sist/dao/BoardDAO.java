package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	// 목록보기
	public ArrayList<BoardVO> boardAllData(int page){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			
			// 한 페이지를 10개로 나누겠다!
			int rowSize=5;
			// SQL쿼리문장에서 : ~ BETWEEN start AND end ~
			// int start = (page*rowSize)-(rowSize-1);
			int start = rowSize*(page-1) + 1;
	
			int end = page * rowSize;
			
			String sql="SELECT no,subject,name,regdate,hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit "
					+ "FROM jsp_board ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?"; 
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BoardVO vo =new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			disConnection();
		}
		
		return list;
		
	}
	
	
	// 총 페이지 가져오기
	public int boardTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/5.0) FROM jsp_board";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		return total;
	}
	
	
	// 내용보기
	public BoardVO boardDetailData(int no)
	{
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			// 내용 보면 조회수 증가시키기
			String sql="UPDATE jsp_board SET "
					+ "hit=hit+1 "
					+ "WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			// 내용보기 데이터 읽기
			sql="SELECT no,name,subject,content,regdate,hit "
					+ "FROM jsp_board "
					+ "WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		
		
		
		return vo;
	}
	

}
