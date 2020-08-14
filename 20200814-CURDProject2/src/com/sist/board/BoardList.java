package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;//ArrayList
import com.sist.dao.*;
@WebServlet("/BoardList")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 브라우저에서 실행하는 화면 => HTML
		// 브라우저에 알림 => HTML문서를 전송할 것이다 
		response.setContentType("text/html;charset=EUC-KR");
		// HTML을 브라우저로 전송 시작 
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".row {margin:0px auto;width:700px}");
		out.println("h2 {text-align:center}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<h2>자유게시판</h2>");
		out.println("<div class=row>");
		
		out.println("<table class=\"table\">");
		out.println("<tr>");
		out.println("<td>");
		out.println("<a href=BoardInsert class=\"btn btn-sm btn-success\">새글</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("<table class=\"table table-hover\">");
		out.println("<tr class=danger>");
		out.println("<th class=text-center width=10%>번호</th>");
		out.println("<th class=text-center width=45%>제목</th>");
		out.println("<th class=text-center width=15%>이름</th>");
		out.println("<th class=text-center width=20%>작성일</th>");
		out.println("<th class=text-center width=10%>조회수</th>");
		out.println("</tr>");
		// 출력 
		BoardDAO dao=new BoardDAO();
		ArrayList<BoardVO> list=dao.boardListData();
		for(BoardVO vo:list)
		{
			out.println("<tr>");
			out.println("<td class=text-center width=10%>"+vo.getNo()+"</td>");
			out.println("<td class=text-left width=45%>"
					
			// ?의 역할 : no라는 매개변수를 주겠따... 주소에 글번호 삽입..?
			+"<a href=BoardDetail?no="+vo.getNo()+">"
			+vo.getSubject()+"</a></td>");
			out.println("<td class=text-center width=15%>"+vo.getName()+"</td>");
			out.println("<td class=text-center width=20%>"+vo.getRegdate().toString()+"</td>");
			out.println("<td class=text-center width=10%>"+vo.getHit()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<hr>");
		
		
		out.println("<table class=\"table\">");
		out.println("<tr>");
		
		out.println("<td class=text-left>");
		out.println("Search:");
		out.println("<select class=input-sm>");
		out.println("<option>이름</option>");
		out.println("<option>제목</option>");
		out.println("<option>내용</option>");
		out.println("</select>");
		out.println("<input type=text size=15 class=input-sm>");
		out.println("<input type=button value=찾기 class=\"btn btn-sm btn-danger\">");
		out.println("</td>");
		
		out.println("<td class=text-right>");
		out.println("<a href=BoardInsert class=\"btn btn-sm btn-primary\">이전</a>");
		out.println("0 page / 0 pages");
		out.println("<a href=BoardInsert class=\"btn btn-sm btn-primary\">다음</a>");
		out.println("</td>");
		
		out.println("</tr>");
		out.println("</table>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}




