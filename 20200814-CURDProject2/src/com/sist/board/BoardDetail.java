package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;


@WebServlet("/BoardDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 브라우저에서 실행하는 화면 => HTML
		// 브라우저에 알림 => HTML문서를 전송할 것이다 
		response.setContentType("text/html;charset=EUC-KR");
		// HTML을 브라우저로 전송 시작 
		PrintWriter out=response.getWriter();
		
		// 번호 받는다
		String no=request.getParameter("no");
		BoardDAO dao= new BoardDAO();
		BoardVO vo=dao.boardDetail(Integer.parseInt(no));
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".row {margin:0px auto;width:600px}");
		out.println("h2 {text-align:center}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<h2>내용보기</h2>");
		out.println("<div class=row>");
		
		// do post 호출 : dopost에 입력한 값을 보내기 
		// 전송방식 (1) 감춰서 post 보안에 조음(2) 노출해서 get
		out.println("<table class=\"table\">");
		
		out.println("<tr>");
		out.println("<td class=\"success text-center\" width=25%>번호</td>");
		out.println("<td width=25% class=text-center>"+vo.getNo()+"</td>");
		out.println("<td class=\"success text-center\" width=25%>작성일</td>");
		out.println("<td width=25% class=text-center>"+vo.getRegdate()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td class=\"success text-center\" width=25%>이름</td>");
		out.println("<td width=25% class=text-center>"+vo.getName()+"</td>");
		out.println("<td class=\"success text-center\" width=25%>조회수</td>");
		out.println("<td width=25% class=text-center>"+vo.getHit()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td class=\"success text-center\" width=25%>제목</td>");
		out.println("<td colspan=3>"+vo.getSubject()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		// valign : top bottom middle
		out.println("<td colspan=4 heigth=200 valign=top></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		// valign : top bottom middle
		out.println("<td colspan=4 class=text-right>");
		out.println("<a href=# class=\"btn btn-xs btn-success\">수정</a>");
		out.println("<a href=# class=\"btn btn-xs btn-warning\">삭제</a>");
		out.println("<a href=BoardList class=\"btn btn-xs btn-danger\">목록</a>");
		
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
