package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.MovieDAO;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".row{");
		out.println("margin:0px auto;");
		out.println("width:300px;");
		out.println("}");
		
		out.println("</style>");

		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=container>");
		out.println("<h1 class=text-center>로그인</h1>");
		out.println("<div class=row>");
		
		out.println("<form method=post action=Login>"); // 포스트로 호출 => 포스트를 갖고 있는 애가 누구야? => 로그인 => 로그인에서 호출
		out.println("<table class=table>");
		
		out.println("<tr>");
		out.println("<td width=20% class=text-right>ID</td>");
		out.println("<td width=75%>");
		out.println("<input type=text size=15 class=input name=id>"); // name이란 속성은 자바에서 값 받기 위해 사용됨
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=20% class=text-right>Password</td>");
		out.println("<td width=75%>");
		out.println("<input type=password size=15 class=input name=pwd>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=2 class=text-center>");
		out.println("<input type=submit value=로그인 class=\"btn btn-sm btn-success\">");
		out.println("<input type=button value=취소 class=\"btn btn-sm btn-danger\">");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id"); // 사용자가 input한 id(name=id)가 response에 담김
		String pwd=request.getParameter("pwd");
//		System.out.println("ID:"+id);
//		System.out.println("PWD:"+pwd);
		MovieDAO dao = new MovieDAO();
		String result=dao.isLogin(id, pwd);
		
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		
		if(result.equals("NOID"))
		{
			//out.println("<>");
			out.println("<script>");
			out.println("alert(\"ID가 존재하지 않습니다\");");
			out.println("history.back()");
			out.println("</script>");
		}
		else if(result.equals("NOPWD"))
		{
			out.println("<script>");
			out.println("alert(\"PWD가 존재하지 않습니다\");");
			out.println("history.back()");
			out.println("</script>");

		}
		else {
			// id를 서버에 저장해야함 & 프로그램 종료시까지 저장
			HttpSession session = request.getSession(); // 세션해제
			session.setAttribute("id",id); // 서버에 저장
			
			
			// 로그인되면 무비메인 페이지로 이동
			response.sendRedirect("MovieMain");
		}
		
	}

}
