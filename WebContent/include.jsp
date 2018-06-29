<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <h3>지시자 포함(정적포함) 하나씩 전체 를 가지고 옴</h3> 
 
 <!-- 서비스내로 들어가기 때문에 method 사용 가능하다, 하지만 변수가 겹칠경우에 변수사용이 불가해진다.
  기본 jsp의 변수를 사용하기 위해서는 정적포함을 하는것이 좋다. -->
 <%@ include file = "first.jsp" %>

<hr>
<!-- 서비스 안에 생성되는게 아니기 때문에 out 메서드 사용이 불가하다. 
 포함될 페이지의 변수를 사용하고 싶을경우에 사용 - 변수가 겹칠경우에도 새로운 변수 처리가 가능하다. -->
  <h3>태그 포함(동적포함) -한줄코드로 읽어옴(열고 닫고 해줘야함) = (실행시에 포함된다)</h3> 
  <jsp:include page = "first.jsp"></jsp:include>

</body>
</html>