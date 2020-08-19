package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.manager.MovieVO;
import com.sist.manager.NewsVO;

public class MovieDAO {
	     // 연결 
		private Connection conn; // 오라클 연결 클래스
		 // SQL문장을 전송 
		private PreparedStatement ps; 
		 // 오라클 주소 첨부 
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		 // 연결 준비 
		 // 1. 드라이버 등록 
		public MovieDAO()
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
		// 연결/닫기 반복 => 기능이 반복일 경우 => 메소드로 처리
		public void getConnection()
		{
			try
			{
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch(Exception ex) {}
		}
		public void disConnection()
		{
			try
			{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex) {}
		}
		
		
		// 기능
		// 1. 저장 => INSERT , UPDATE , DELETE => 결과값이 없으니까 void * SELECT 는 검색 결과를 반환하니까 void(x)
		public void movieInsert(MovieVO vo) {
			try {
				getConnection();
				String sql="INSERT INTO daum_movie VALUES( "
						+ "(SELECT NVL(MAX(no)+1,1) FROM daum_movie),?,?,?,?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getCateno());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getPoster());
				ps.setString(4, vo.getRegdate());
				ps.setString(5, vo.getGenre());
				ps.setString(6, vo.getGrade());
				ps.setString(7, vo.getActor());
				ps.setString(8, vo.getScore());
				ps.setString(9, vo.getDirector());
				ps.setString(10, vo.getStory());
				ps.setString(11, vo.getKey());
				
				ps.executeUpdate();
			
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			finally {
				disConnection();
			}
		}
		
		public void newsInsert(NewsVO vo) {
			try {
				getConnection();
				String sql="INSERT INTO daum_news VALUES(?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getPoster());
				ps.setString(3, vo.getLink());
				ps.setString(4, vo.getContent());
				ps.setString(5, vo.getAuthor());
				
				ps.executeUpdate();
			
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			finally {
				disConnection();
			}
		}
		
		public ArrayList<MovieVO> movieListData(int cno)
		{
			ArrayList<MovieVO> list = new ArrayList<MovieVO>();
			try {
				getConnection();
				String sql="SELECT poster,title,no FROM daum_movie WHERE cateno=?";
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, cno);
				
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					MovieVO vo = new MovieVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setNo(rs.getInt(3));
					
					list.add(vo);
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				disConnection();
			}
			return list;
		}
		
		public ArrayList<NewsVO> newsListData()
		{
			ArrayList<NewsVO> list = new ArrayList<NewsVO>();
			try {
				getConnection();
				String sql="SELECT title,poster,link,content,author FROM daum_news";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					NewsVO vo = new NewsVO();
					vo.setTitle(rs.getString(1));
					vo.setPoster(rs.getString(2));
					vo.setLink(rs.getString(3));
					vo.setContent(rs.getString(4));
					vo.setAuthor(rs.getString(5));
					
					list.add(vo);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				disConnection();
			}
			
			return list;
		}
		
}
