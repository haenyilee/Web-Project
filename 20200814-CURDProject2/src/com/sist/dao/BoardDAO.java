package com.sist.dao;
import java.util.*;
import java.sql.*;
public class BoardDAO {
     // 연결 
	private Connection conn; // 오라클 연결 클래스
	 // SQL문장을 전송 
	private PreparedStatement ps; 
	 // 오라클 주소 첨부 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	 // 연결 준비 
	 // 1. 드라이버 등록 
	public BoardDAO()
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
	// 1. 목록 (게시판) => SELECT
	public ArrayList<BoardVO> boardListData()
	{
		ArrayList<BoardVO> list=
				new ArrayList<BoardVO>();
		try
		{
			// 연결
			getConnection();
			// SQL문장 전송 
			String sql="SELECT no,subject,name,regdate,hit FROM freeboard ORDER BY no DESC";// 최신 등록된 게시물 먼저 출력
			          // ORDER BY => 단점 (속도가 늦다) => INDEX
			ps=conn.prepareStatement(sql);
			// SQL실행후에 결과값 받기
			ResultSet rs=ps.executeQuery();
			// 결과값을 => ArrayList에 첨부
			while(rs.next())// 출력한 첫번째줄부터 마지막줄까지 읽어 온다
			{
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	// 2. 내용보기  => SELECT (WHERE) ?no=1
	public BoardVO boardDetail(int no)
	{
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			// 1. 조회수 증가
			String sql="UPDATE freeboard SET hit=hit+1 WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			// 물음표에 값채우기
			ps.setInt(1, no);
			
			// 실행
			ps.executeUpdate();
			
			// 2. 내용볼 데이터를 가지고 온다
			sql="SELECT no,name,subject,content,regdate,hit FROM freeboard WHERE no=?";
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
	
	// 3. 글쓰기    => INSERT
	public void boardInsert(BoardVO vo)
	{
		try {
			getConnection();
			String sql="INSERT INTO freeboard(no,name,subject,content,pwd) "
						// no는 자동으로 증가시켜야함 : null+1=null이므로 NVL꼭 줘야함
					+ "VALUES((SELECT NVL(MAX(no)+1,1) FROM freeboard),?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			//executequery와 차이점 : executeUpdate()은 autocommit!(insert,update
			ps.executeUpdate();
		} catch (Exception e) {
			
			
		} finally {
			disConnection();
		}
		
	}
	
	// 4. 글수정    => UPDATE
	// 5. 글삭제    => DELETE
	// 6. 찾기       => SELECT (LIKE)
	
}





