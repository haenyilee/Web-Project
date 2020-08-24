<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 자바영역 => 문법이 자바와 동일
		// 변수 선언 (지역변수) => int i=0;
		// 객체 메모리 할당 => Date date = new Date()
		// 제어문
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
	
	%>
	오늘 날짜는 <%=today %>입니다. <br>
	오늘 날짜는 <% out.println(today); %>입니다.

</body>
</html>