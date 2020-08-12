package com.sist.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 설치
	public EmpDAO()
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
	
	// EmpVO와 DeptVO의 객체를 담을 ArrayList 생성
	public ArrayList<EmpVO> empDeptJoinData()
	{
		ArrayList<EmpVO> list=
				new ArrayList<EmpVO>();
		
		// 쿼리 작성 & 전송 & 컬럼별 데이터 저장 과정
		try {
			getConnection();
			
			// 쿼리 작성
			// 왜 아래문장은 오류나지?
			// String sql="SELECT empno,ename,job,hiredate,TO_CHAR(sal,'$9,999'),emp.deptno,dname,loc FROM emp,dept WHERE emp.deptno=dept.deptno";
			String sql="SELECT empno,ename,job,hiredate,sal,emp.deptno,dname,loc FROM emp,dept WHERE emp.deptno=dept.deptno";
					// 자바에서는 쿼리 맨 뒤에 ; 작성하면 오류남
			
			// 쿼리 전송
			ps=conn.prepareStatement(sql);
			
			// 쿼리 전송 후 받은 결과데이터 rs 메모리에 받아두기
			ResultSet rs = ps.executeQuery();
			
			// EmpVO는 한개에 대한 값만 저장하고 있으므로, 행의 갯수만큼 반복해서 각 행에 데이터 넣어주기
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				
				// DeptVO의 객체들에 rs값 담아주기
				vo.setSal(rs.getInt(5));
				vo.setDeptno(rs.getInt(6));
				vo.getDvo().setDname(rs.getString(7));
				vo.getDvo().setLoc(rs.getString(8));
				
				// 리스트 vo에 값 추가
				list.add(vo);				
			}
			
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		return list;
	}
	
}