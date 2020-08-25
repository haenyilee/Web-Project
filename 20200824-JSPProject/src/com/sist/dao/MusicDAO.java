package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import sun.security.util.Length;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public MusicDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	public ArrayList<MusicVO> musicAllData(){
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql="SELECT mno,title,singer,album,poster "
					+ "FROM genie_music ORDER BY mno";
			ps=conn.prepareStatement(sql);
			// 결과값 받기
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				vo.setPoster(rs.getString(5));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	
	public ArrayList<DaumMovieVO> daumMovieAllData(int page){
		ArrayList<DaumMovieVO> list = new ArrayList<DaumMovieVO>();
		try {
			getConnection();
			int rowSize=10;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			System.out.println(start);
			System.out.println(end);
			String sql="SELECT no,title,poster,actor,score,director,num "
					+ "FROM (SELECT no,title,poster,actor,score,director,rownum as num "
					+ "FROM (SELECT no,title,poster,actor,score,director "
					+ "FROM daum_movie "
					+ "WHERE cateno=1 "
					+ "ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				DaumMovieVO vo = new DaumMovieVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setActor(rs.getString(4));
				vo.setScore(rs.getString(5).substring(3));
				vo.setDirector(rs.getString(6));
				
				list.add(vo);
				
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	public int daumMovieTotalPage() 
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM daum_movie WHERE cateno=1";
			
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			disConnection();
		}
		return total;
	}
	
}
