package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.MovieDAO;


@WebServlet("/MovieDelete")
public class MovieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// MovieDelete?no="+vo.getNo() => no를 MovieDelete로 보내라는 뜻
	
	/*
	 * GET방식(노출하기) => <a> response.sendRedirect()
	 * POST방식(감추기) => form
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no=request.getParameter("no");
		String mno=request.getParameter("mno");
		
		// DAO 전송
		MovieDAO dao=new MovieDAO();
		
		// 삭제처리
		dao.replyDelete(Integer.parseInt(no));
		
		// MovieDetail로 이동
		response.sendRedirect("MovieDetail?no="+mno);
	}

}
