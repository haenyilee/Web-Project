<%@page import="com.sist.dao.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 
    
    page 매개변수는 아래  
    	<a href="DaumMovie.jsp?page=<%=curpage>1?curpage-1:curpage %>">
	에서 page= 뒤의 값을 받아오는 것이다~!!!!!! 
	첫 페이지를 딱 들어가게 되면 클릭한 것이 없으니 page값을 받을 수가 없다.
	그래서 page가 없으니 strPage도 당연히 없고, 이때는 페이지가 1이다 라고 명시해줘야한다!
	
	--%>


<%
	String strPage = request.getParameter("page");
	if(strPage==null)
	{
		strPage="1";
	}

	MusicDAO dao= new MusicDAO();
	int curpage=Integer.parseInt(strPage);
	
	int totalpage=dao.daumMovieTotalPage();
	ArrayList<DaumMovieVO> list = dao.daumMovieAllData(curpage);
	
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
	<table border=1 width=1300>
		<tr>
			<td align=left></td>
			<td align=right>
				<a href="DaumMovie.jsp?page=<%=curpage>1?curpage-1:curpage %>">이전</a>
				<%=curpage %> / <%=totalpage %> pages
				<a href="DaumMovie.jsp?page=<%=curpage<=totalpage?curpage+1:curpage %>">다음</a>
			</td>
		</tr>
	</table>

</table>
</center>
</body>
</html>
