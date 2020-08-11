package com.sist.dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		// HTML 제작
		PrintWriter out=response.getWriter();
		// response(응답) 브라우저 전송용 , request 사용자가 보낸 데이터를 받는 경우
		
		out.println("<html>");
		
			/*
			// 제목
			out.println("<head>");
				out.println("<style>");
				out.println("<script>"); => 이벤트처리		
			out.println("</head>");
			*/ 
			
			// 화면출력
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>LOGIN</h1>");
		out.println("<form method=post action=LoginServlet>");
				
			out.println("<table width=250>");
			out.println("<tr>");
			out.println("<td width=15% align=right>이름</td>");
			out.println("<td width=85% >");
			out.println("<input type=text name=ename size=17>"); // 입력창
			out.println("</tr>");
					
			out.println("<tr>");
			out.println("<td width=15% align=right>사번</td>");
			out.println("<td width=85% >");
			out.println("<input type=password name=empno size=17>"); // 입력창
			out.println("</tr>");

					
			out.println("<tr>"); // 다음줄로 내려주는 기능
			out.println("<td align=center colspan=2>");
			out.println("<input type=submit value=로그인>");						
			out.println("</td>");
			out.println("</tr>");
				
			out.println("</table>");
			
		out.println("</form>");		
		out.println("</center>");
		out.println("</body>");		
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청처리
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
				
		String ename=request.getParameter("ename"); // "ename"은 html name태그의 ename
		String empno=request.getParameter("empno");
		//System.out.println("이름:"+ename.toUpperCase());
		//System.out.println("사번:"+empno);
		MyDAO dao= new MyDAO();
		String result=dao.isLogin(ename.toUpperCase(), Integer.parseInt(empno));
		// 웹에서 받는 내용은 다 string 형식으로 받아짐  => 그래서 패스워드는 정수형으로 변환해줘야함
		
		if(result.equals("NONAME")) // 이름이 없는 경우
		{
			out.println("<script>");
			out.println("alert(\"이름이 존재하지 않습니다\");");
			out.println("history.back();"); // 이전화면으로 돌아가기 (back버튼과 같음)
			out.println("</script>");			
		}
		else if(result.equals("NOSABUN")) // 사번이 틀린 경우
		{
			out.println("<script>");
			out.println("alert(\"사번이 존재하지 않습니다\");");
			out.println("history.back();");
			out.println("</script>");			
		}
		else // 로그인된 경우
		{
			/*
			out.println("<script>");
			out.println("alert(\""+result+"님 메인으로 이동합니다\");");
			out.println("</script>");		
			*/
			response.sendRedirect("MusicServlet"); // 로그인되면 지니뮤직차트 페이지로 이동하기
		}
	}

}
