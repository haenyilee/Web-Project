<%@page import="com.sist.dao.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String strPage = request.getParameter("page");
	if(strPage==null)
		strPage="1";
	/*
		request VS response
		: https://github.com/haenyilee/HTML_Basic/wiki/JSP_request-VS-response
	*/



	BoardDAO dao = new BoardDAO();
	int curpage=Integer.parseInt(strPage);
	//curpage=1;
	int totalpage=dao.boardTotalPage();
	ArrayList<BoardVO> list = dao.boardAllData(curpage);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
	<center>
	<h1>게시판</h1>
	<table class="table_content" width=1300>
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
			<tr class="dataTr">
				<td width=10% class="tdcenter"><%=vo.getNo() %></td>
				<td width=45% class="tdcenter"><%=vo.getSubject() %></td>
				<td width=15% class="tdcenter"><%=vo.getName() %></td>
				<td width=20% class="tdcenter"><%=vo.getRegdate() %></td>
				<td width=10% class="tdcenter"><%=vo.getHit() %></td>													
			</tr>
		<%		  
		  }
		%>

	</table>
	<table class="table_content" width=1300>
		<tr>
        <td align=left></td>
        <td align="right">
				<a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>">이전</a>
				<%=curpage %> page / <%=totalpage %> pages
				<a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>">다음</a>
		</td>
		</tr>
	</table>
	
	</center>

</body>
</html>