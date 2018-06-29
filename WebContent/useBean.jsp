<%@page import="com.my.vo.Customer"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%--//커스터머 속성값 얻어오기
  com.my.vo.Customer customer = (Customer)request.getAttribute("customer");
  // 속성값이 null인경우 객체생성 후 속성명 추가
  if(customer == null){
    customer = new com.my.vo.Customer();
  	request.setAttribute("Customer", customer);
  }
--%>

<%-- <jsp:useBean id="customer"   class="com.my.vo.Customer"   scope="application" ></jsp:useBean>
<jsp:useBean id="customer"   class="com.my.vo.Customer"   scope="session" ></jsp:useBean>
<jsp:useBean id="customer"   class="com.my.vo.Customer"   scope="page" ></jsp:useBean>

// 속성객체의 id 값을 설정한다. ("id1");

customer.setId("Id")
// 단일 클래스라면 문제없지만 jsp:getProperty 는 유연성이 없고, 제약성이 크다보니 사용할 수 있는 상황이 많지 않다.
 --%>
 
 
<jsp:useBean id="customer"   class="com.my.vo.Customer"   scope="request" ></jsp:useBean>
<jsp:setProperty name="customer"  property="id"  value = "id1" />
<jsp:setProperty name="customer"  property="password"  value = "p1" />

<jsp:getProperty name="customer"  property="id"/>



<!DOCTYPE html>
<html>
<head>
<metacharset="UTF-8">
<title>useBean.jsp</title>
</head>
<body>





</body>
</html>