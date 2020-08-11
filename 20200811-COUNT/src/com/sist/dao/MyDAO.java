package com.sist.dao;
import java.sql.*;
import java.util.*;

public class MyDAO {
	// 연결객체
	private Connection conn;
	
	// SQL전송 객체
	private PreparedStatement ps;
	
	// URL오라클 주소 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	public MyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 오라클 연결 메소드 : 명령어(conn)
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");			
		} catch (Exception e) {}
	}
	
	// 오라클 연결 해제 메소드 : 명령어(exit)
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	// JDBC(원시소스) => DBCP(커넥션관리) => ORM(MyBatis,Hibernate)
	// 기능
	public String isLogin(String ename,int empno)
	{
		String result="";
		try {
			getConnection();
			// sql문장 전송
			String sql="SELECT COUNT(*) FROM emp WHERE ename=?"; // 값이 있는지 없는지 확인하려면 COUNT(*)사용하기!
			ps=conn.prepareStatement(sql);
			
			// ?(물음표)에 값 채우기
			ps.setString(1, ename);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int count=rs.getInt(1); // 없으면 0, 있으면 1			
			rs.close();
			if(count==0) // 사원 이름이 존재하지 않으면
			{
				result="NONAME";
			}
			else // 사원이름이 존재
			{
				sql="SELECT empno FROM emp WHERE ename=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, ename);
				rs=ps.executeQuery();
				rs.next(); // 결과 데이터가 있는 위치로 커서위치를 이동
				int db_empno=rs.getInt(1);
				rs.close();
				
				if(empno==db_empno) // 로그인
				{
					result=ename;
				}
				else // 사번이 틀린 상태
				{
					result="NOSABUN";
				}
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		
		return result;
	}

}
