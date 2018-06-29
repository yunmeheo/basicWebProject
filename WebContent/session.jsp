<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>session.jsp</title>
</head>



<h3>서블릿 소스에서는 세션객체사용</h3>  <h5>HttpSession s = request.getSession();</h5>

<h3> jsp에서는 세션객체사용 </h3>   <h5>session.getId()</h5>


SESSION ID : <%= session.getId()%> <br>
IS NEW : <%= session.isNew()%><br>
최종사용시간 : <%= new Date(session.getLastAccessedTime()) %>

<h3> 시간별 섹션객체 소멸 </h3> <h5>setMaxInactiveInterval(10); </h5>
<%--  <%session.setMaxInactiveInterval(10); %>--%>

<h3> 즉시섹션객체 소멸 </h3> <h5>session.invalidate(); </h5>
<%session.invalidate(); %>


<body>

</body>
</html>