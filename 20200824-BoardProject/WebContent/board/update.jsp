<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	// 사용자가 보낸 게시물 번호 받기
	// update.jsp?no=5 => 5번 데이터를 보냄
	String no = request.getParameter("no");
	BoardDAO dao=new BoardDAO();
	BoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
	<%--
	submit
	
	 --%>
	<center>
	<h1>수정하기</h1>
	<form method=post action=update_ok.jsp>
		<table class="table_content" width=800>
			<tr>
				<th width=15% align=center>이름</th>
				<td width=85%>
					<input type=text name="name" size=40 required value=<%=vo.getName() %>>
					
					<%-- hidden : 데이터는 보여주지 않고 넘길 때 사용하는 것 --%>
					<input type=hidden name=no value=<%=no %>>
				</td>
			</tr>		
		
		
			<tr>
				<th width=15% align=center>제목</th>
				<td width=85%>
				
					<%-- 제목은 공백을 무조건 넣어서 입력할 것이기 때문에 큰따옴표를 꼭 같이 적어줘야함 "<%=vo.getSubject()%>" --%>
					<input type=text name="subject" size=90 required value="<%=vo.getSubject()%>">
				</td>
			</tr>
			
			<tr>
				<th width=15% align=center>내용</th>
				<td width=85%>
					<textarea rows=7 cols=90 name="content" required>
					<%=vo.getContent() %>
					</textarea>
				</td>
			</tr>
			
			
			<tr>
				<th width=15% align=center>비밀번호</th>
				<td width=85%>
					<input type=password name="pwd" size=10 required> 
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align=center>
					<input type=submit value="수정">
					<input type=button value="취소" onclick="javascript:history.back()">
				</td>
			</tr>
		</table>
	</form>
	</center>
</body>
</html>