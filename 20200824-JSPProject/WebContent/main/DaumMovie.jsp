<%@page import="com.sist.dao.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MusicDAO dao= new MusicDAO();
	ArrayList<DaumMovieVO> list = dao.daumMovieAllData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<h1>다음 현재 상영 영화</h1>
	<table border=1 width=1300>
	<tr>
		<th>no</th>
		<th>제목</th>
		<th>포스터</th>
		<th>출연</th>
		<th>평점</th>
		<th>감독</th>
	</tr>
	<% 
	for(DaumMovieVO vo:list)
	{
	%>
	<tr>
		<td><%=vo.getNo() %></td>
		<td><%=vo.getTitle() %></td>
		<td>
		<img src=<%=vo.getPoster() %> width=100 height=100>
		</td>
		<td><%=vo.getActor() %></td>
		<td><%=vo.getScore() %></td>
		<td><%=vo.getDirector() %></td>
	</tr>

	<% 
	} 
	%>

</table>
</center>
</body>
</html>