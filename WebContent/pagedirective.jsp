<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage = "err.jsp" %>    
<%@page buffer="1000kb" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% for(int i=0; i<1000 ; i++){
 %>	
  <span><%=i %></span>
<%
}

    //(a.txt파일 없을 때) try catch로 예외처리하기 
    /* try{
    //c:\\a.txt파일 읽기
    FileInputStream fis = null;
    fis = new FileInputStream("c:\\a.txt");
    
    //a.txt 파일이 없더라도 스크립틀립내부에서 서비스메서드 throws가 선언 되어있기때문에 익셉션이 발생되지 않는다.
    //버퍼의 크기를 조절하면 예외발생이 가능하다.
    }catch(Exception e){
      request.setAttribute("e", e);
      RequestDispatcher rd = request.getRequestDispatcher("err.jsp");
      rd.forward(request, response);
    }	 */
    
    
    //(a.txt파일 없을 때) try catch 없이 예외처리하기
    FileInputStream fis = null;
    fis = new FileInputStream("c:\\a.txt");


%>

</body>
</html>