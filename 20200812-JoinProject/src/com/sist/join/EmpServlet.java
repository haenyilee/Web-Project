package com.sist.join;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	// doGet함수는 run()함수? 쓰레드 함수로 동작... doGet함수가 곧 쓰레드함수... 쓰레드니까 한사람당 하나씩 따로 있음
	// doGet함수는 서비스하는 함수니까 이용자마다 따로 작동해야함
	// 모든 이용자가 동일한 데이터를 가지고 있지만 따로 작동할 수 있도록....
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=EUC-KR");
		// 접속하는 사람마다  out이 다르기 때문에 
		PrintWriter out=response.getWriter();
		
		EmpDAO dao=new EmpDAO();
		
		ArrayList<EmpVO> list = dao.empDeptJoinData();
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>사원정보</h1>");
		out.println("<table border=1 width=700>");
		
		out.println("<tr>");
		out.println("<th>사번</th>");
		out.println("<th>이름</th>");
		out.println("<th>직위</th>");
		out.println("<th>입사일</th>");
		out.println("<th>급여</th>");
		out.println("<th>부서번호</th>");	
		
		out.println("<th>부서명</th>");
		out.println("<th>근무지</th>");
		out.println("</tr>");
		
		
		// 14개의 row에 대한 데이터 출력하기
		for(EmpVO vo:list)
		{
			// EmpVO의 값
			out.println("<tr>");
			out.println("<th>"+vo.getEmpno()+"</th>");
			out.println("<th>"+vo.getEname()+"</th>");
			out.println("<th>"+vo.getJob()+"</th>");
			out.println("<th>"+vo.getHiredate()+"</th>");
			out.println("<th>"+vo.getSal()+"</th>");
			out.println("<th>"+vo.getEmpno()+"</th>");
			
			// DeptVO의 값
			out.println("<th>"+vo.getDvo().getDname()+"</th>");
			out.println("<th>"+vo.getDvo().getLoc()+"</th>");
			out.println("</tr>");
		}
		
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
