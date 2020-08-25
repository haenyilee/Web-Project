<%@page import="com.sist.dao.BoardDAO"%>
<%@page import="com.sist.dao.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--

전송받은 데이터를 받아서 오라클에 연결만 시켜주는 파일
(형식 : _ok.jsp)

** _ok.jsp가 필요한 이유?**
리스트에서 실행해서 클릭하면 글쓰기로 넘어가는데 
글쓰기 기능에서 insert할 수 없음

html보다 자바가 먼저 실행되기 때문에 글쓰기 기능에 데이터 넘겨주는 기능을 추가해버리면
내용을 입력하지 않았는데도 데이터가 넘어가게 되기 때문에

글쓰기 창에서는 입력만 하도록 유도하고
글쓰기 버튼을 클릭해야 데이터가 넘어가도록  해야함

--%>


<%
	try
	{
		request.setCharacterEncoding("UTF-8");
	} catch(Exception e){}

	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	// 데이터 모아서 DAO로 전송
	BoardVO vo = new BoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// DAO연결 => 오라클에 입력받은 값 INSERT
	BoardDAO dao= new BoardDAO();
	dao.boardInsert(vo);
						
	// list로 이동
	response.sendRedirect("list.jsp");


%>

