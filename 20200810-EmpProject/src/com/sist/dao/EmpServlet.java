package com.sist.dao;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		
		EmpDAO dao= new EmpDAO();
		ArrayList<EmpVO> list = dao.empAllData();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>사원정보</h1>");
		out.println("<table width=1000 border=1 bordercolor=black>");
		
		out.println("<tr>");		
		out.println("<th>사번</th>");
		out.println("<th>이름</th>");
		out.println("<th>직위</th>");
		out.println("<th>사수</th>");
		out.println("<th>입사일</th>");		
		out.println("<th>급여</th>");
		out.println("<th>성과급</th>");
		out.println("<th>부서</th>");
		out.println("</tr>");		
		
		for(EmpVO vo:list)
		{
			out.println("<tr>");
			
			out.println("<td>"+vo.getEmpno()+"</td>");
			out.println("<td>"+vo.getEname()+"</td>");
			out.println("<td>"+vo.getJob()+"</td>");
			out.println("<td>"+vo.getMgr()+"</td>");
			out.println("<td>"+vo.getHiredate()+"</td>");
			out.println("<td>"+vo.getSal()+"</td>");
			out.println("<td>"+vo.getComm()+"</td>");
			out.println("<td>"+vo.getDeptno()+"</td>");		

			out.println("</tr>");
			
		}

		

		out.println("</table>");				
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}
