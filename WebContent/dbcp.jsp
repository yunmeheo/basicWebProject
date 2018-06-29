<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>dbcp.jsp</title>
</head>
<body>

<% Context initContext = new InitialContext();
//이니셜컨텍스트객체를 생성 해 두고
Context envContext  = (Context)initContext.lookup("java:/comp/env");

//데이터소스타입으로 다운캐스팅을 하고
DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");

//지금 생성되어있는 객체중 놀고있는 커넥션을 받아온다.
Connection conn = ds.getConnection();
%>
<!-- DBCP를 활용한 Connection얻기 성공! -->

</body>
</html>