package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	private final String URL="jdbc:oracle:thin:@localohost:1521:XE";
	
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
					+ "FROM genie_music";
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
			
		}
		return list;
	}
	
}
