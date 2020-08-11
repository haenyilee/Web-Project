package com.sist.dao;
// DATA ACCESS
import java.util.*;
import java.sql.*;

public class MusicDAO {
	// 오라클 연결
	private Connection conn;
	
	// 쿼리문장
	private PreparedStatement ps;
	
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 설치
	public MusicDAO()
	{
		// 생성자의 역할 (1) 멤버변수의 초기화 (2) 네트워크 서버 연결 -> 시작하자마자 서버 연결위함
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 연결
	public void getConnection() {
		try {
			// 연결 명령어 : conn hr/happy
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {}
	}
	
	// 연결 종료
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	// 쿼리 문장전송 => 200개의 음악 데이터 가져올 것임
	public ArrayList<MusicVO> musicAllData(){
		ArrayList<MusicVO> list = new ArrayList<MusicVO>();
		
		try {
			// 오라클 연결
			getConnection();
			
			// sql문장 전송
			String sql="SELECT mno,poster,title,singer,album FROM genie_music ORDER BY mno";
			
			ps=conn.prepareStatement(sql); // excuteQuery()전까지 실행 안된 상태임, 문장만 쳐진 상태(엔터치지 전)
			
			// 결과값 받기
			ResultSet rs= ps.executeQuery(); // 실행결과를 rs에 다 담아둔상황
			while(rs.next()) // 밑에서부터 위로 올라가면서 데이터 읽으려면 previous()
			{
				// 200개를 메모리에 따로 저장
				// ArrayList에 값 채우기
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));// vo.setMno(mno);
				vo.setPoster(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setSinger(rs.getString(4));
				vo.setAlbum(rs.getString(5));
				
				// 200개를 모아서 브라우저로 전송
				list.add(vo);
			}
			rs.close();
			
			
		} catch (Exception e) {
			// 에러 종류 확인
			System.out.println(e.getMessage());
		} finally {
			// 서버 종료
			disConnection();
		}
		
		return list;
	}
	
	// 상세보기
	public MusicVO musicDetailData(int mno)
	{
		MusicVO vo = new MusicVO();
		
		try {
			getConnection();
			String sql="SELECT mno,title,singer,album,poster,key FROM genie_music WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			rs.next();
			// 값을 채운다
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSinger(rs.getString(3));
			vo.setAlbum(rs.getString(4));
			vo.setPoster(rs.getString(5));
			vo.setKey(rs.getString(6));
			
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		
		return vo;
	}
	

}
