<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!----------------------------------------------------------->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("result","hello");
%>
</head>
<body>
	<%= request.getAttribute("result") %> 입니다.	<br> 
	${result} 입니다.	<br>
	${requestScope.result}	<br>
	${names[0]}	<br>
	${names[1]}	<br>
	${notice.id}	<br>
	${notice.title}	<br>
	${result}	<br>
	${param.n} <br>
	${header.accept} <br>
</body>
</html>