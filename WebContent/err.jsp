<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>

<!--  ↑   이페이지는 예외처리 전용 페이지라는 뜻이다. exception 라는 내장객체가 생성된다.-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: gray">

<%
//Throwable e = (Throwable)request.getAttribute("e");
%>

<%-- <h1> 오류내용 : <%=e.getMessage() %></h1> --%>

<h1> 오류내용 :
<%= exception.getMessage() %></h1>

<h1></h1>

</body>
</html>