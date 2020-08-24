<%@page import="com.sist.dao.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> list = dao.boardAllData();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<h1>게시판</h1>
	<table border=1 width=1300>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		
		<%
		  for(BoardVO vo:list)
		  {
		%>	
			<tr>
				<td><%=vo.getNo() %></td>
				<td><%=vo.getSubject() %></td>
				<td><%=vo.getName() %></td>
				<td><%=vo.getRegdate() %></td>
				<td><%=vo.getHit() %></td>													
			</tr>
		<%		  
		  }
		%>

	</table>
	</center>

</body>
</html>