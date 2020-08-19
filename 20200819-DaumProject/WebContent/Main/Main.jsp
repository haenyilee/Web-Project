<!-- jsp는 기반이 html
html안에 자바가 들어감 

서블릿은 기반이 자바
자바 안에 html이 들어감-->

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String name="홍길동";
	%>
	<h1><%=name %></h1>
</body>
</html>