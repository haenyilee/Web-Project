package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;


@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	// 폼작업(화면출력)
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
				out.println(".row {margin:0px auto;width:500px}");
				out.println("h2 {text-align:center}");
				out.println("</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class=container>");
				out.println("<h2>글쓰기</h2>");
				out.println("<div class=row>");
				
				// do post 호출 : dopost에 입력한 값을 보내기 
				// 전송방식 (1) 감춰서 post 보안에 조음(2) 노출해서 get
				out.println("<form method=post action=BoardInsert>");
				out.println("<table class=\"table\">");
				out.println("<tr>");
				out.println("<td width=15% class=text-right>이름</td>");
				out.println("<td width=8% class=text-right>");
				out.println("<input type=text size=15 class=input-sm name=name>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=15% class=text-right>제목</td>");
				out.println("<td width=85% class=text-right>");
				out.println("<input type=text size=15 class=input-sm name=subject>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=15% class=text-right>내용</td>");
				out.println("<td width=85%>");
				out.println("<textarea cols=50 rows=10 name=content></textarea>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=15% class=text-right>비밀번호</td>");
				out.println("<td width=85% class=text-right>");
				out.println("<input type=password size=10 class=input-sm name=pwd>"); // name은 입력 받은 데이터들을 구분할 수 있게 별칭을 준 것임
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan=2 class=text-center>");
				out.println("<input type=submit class=\"btn btn-sm dtn-danger\" value=글쓰기>");
				out.println("<input type=submit class=\"btn btn-sm dtn-info\" value=취소  oneclick=\"javascript:history.back()\">");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</form>");
				
				out.println("</div>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
	}


	
	// 데이터베이스 연결 => 요청처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request에 사용자가 요청한 값이 담겨 있는데, 여기서 값을 가져와야함 반대로 HTML 보낼떄는 response에 담아서 보내면됨~!
		
		// 한글은 디코딩해야함
		try {
			request.setCharacterEncoding("EUC-KR");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		/*
		 * System.out.println("이름:"+name); System.out.println("제목:"+subject);
		 * System.out.println("내용:"+content); System.out.println("비밀번호:"+pwd);
		 */
		BoardVO vo= new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO로 VO에 묶은 값들을 전달하면 => INSERT하라고 SQL문장 전송해서 => 데이터에 쌓으면됨
		BoardDAO dao=new BoardDAO();
		dao.boardInsert(vo);
		
		// 데이터 쌓고나서 다시 목록 화면으로 이동하도록 처리
		response.sendRedirect("BoardList");
		
		
		
	}

}
