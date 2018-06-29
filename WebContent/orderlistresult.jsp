<%@ page import="com.my.vo.OrderLine"%>
<%@ page import="com.my.vo.OrderInfo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.my.dao.OrderDAOOrcle"%>
<%@ page import="com.my.vo.Product"%>
<%@ page import="java.util.HashMap"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>orderlistresult.jsp</title>
</head>

<Style>

table td{
border: 1px dotted;
border-color : #4682B4;
border-radius: 5px;
font-family: sans-serif;
font-size: 12px;
text-align:center;
}

#main{
/* border: 1px dotted; */
font-family: sans-serif;
font-size: large;
background-color: #F0F8FF;
text-align:center;
height : 30px;
text-align:center;
}

table{
border-radius: 5px;
border: 1px solide;
border-collapse: collapse; 

}

table tr{
height: 23px;
}

</Style>
<body>
<table width ="700px" >

<h5>주문 고맙습니다.</h5><br>
<h3>소중한 고객님의 주문내역 입니다.</h3>
  <tr id="main">
  <td>주문번호</td>
  <td>주문일자</td>
  <td>상품번호</td>
  <td>상품명</td>
  <td>상품가격</td>
  <td>수량</td>
  <td>금액</td>
  </tr>
  
  <!-- 변수선언 -->

  <c:set var="list" value="${sessionScope.list}"/>

  <!-- 인포for -->
  
      <c:forEach var="orderInfo"  items= "${list}" > 
     <tr>
      <%-- <%=request.getContextPath() %>
      ${pageContext.request.contextPath}
      <%=list.size()%>
      <%@taplib prefix="fn" uri="http;//java.sum.com/jsp/jstl/functions" %>
      {Infos.lines.size}
      ${fn:length(list)}
      ${fn:length(Infos.lines)} --%>
      <!-- <td rowspan="{Infos.lines.size}"> {orderInfos.Info_no}</td>
      <td rowspan="{Infos.lines.size}"> {orderInfos.Info_date}</td> -->
      
      
      <td rowspan ="${fn:length(orderInfo.lines)}">${orderInfo.info_no} </td>
      <td rowspan ="${fn:length(orderInfo.lines)}">${orderInfo.info_date}</td> 
      <%-- <td>${orderInfo.info_no} </td>
      <td>${orderInfo.info_date}</td> --%>
      
      <!-- </tr>  -->
       
       <!-- 라인for -->
     
        <c:forEach var="orderLine"  items= "${orderInfo.lines}" > 
      <!--  <tr> -->
        <td>${orderLine.line_prod_no.prod_no} </td>
        <td>${orderLine.line_prod_no.prod_name} </td>
        <td>${orderLine.line_prod_no.prod_price}</td>
        <td>${orderLine.line_quantity} </td>
        <td>${orderLine.line_quantity*orderLine.line_prod_no.prod_price} </td>
        </tr> 
        </c:forEach> 
     </c:forEach> 

</table>
</body>
</html>