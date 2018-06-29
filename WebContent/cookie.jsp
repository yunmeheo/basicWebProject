<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


   <!--서버단-->
  <h3>쿠키목록</h3>  
  <%
  //요청헤더에 실려온 쿠키찾기
  
    Cookie[] cs = request.getCookies();
  if(cs != null){
    for(Cookie c : cs){
  
  // 출력하기위해 스크립틀립을 따로 열어준다. 
  %> 
  <%=c.getName() %>: <%=c.getValue() %> <br>
  <%	
    }
  }
  %>
  <hr>
  
  <!--서블릿에서 할일-->
  <%
    //쿠키를 설정하자.
    String name = request.getParameter("name");
    String value = request.getParameter("value");
   //쿠키값이 있을 경우에만 값을 받아준다.
    if(name !=null && !name.equals("") && value != null&& !value.equals("")){
    Cookie c = new Cookie(name, value);
    c.setMaxAge(60);
    response.addCookie(c);
    }
  %>
  
  
  
  <!--클라이언트단-->
  
  <a href ="cookie.jsp">쿠키보기</a>
  <form method ="get" action = "cookie.jsp">
   쿠키이름:<input type="text" name = "name"><br>
   쿠키값:<input type="text" name = "value"><br>
   <input type="submit"  value = "쿠키추가">
  </form>
  
  

</body>
</html>