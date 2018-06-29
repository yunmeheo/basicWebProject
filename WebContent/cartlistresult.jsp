<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.util.Set"%>
<%@ page import="com.my.vo.Product"%>
<%@ page import="java.util.HashMap"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>cartlistresult.jsp</title>
</head>

<Style>

#main{
border: 1px dotted;
font-family: sans-serif;
font-size: large;
background-color: #F0F8FF;
text-align:center;
}


.cartlist{
border-radius: 5px;
border: 1px solide;
border-collapse: collapse; 
}

.cartlist td{
border: 1px dotted;
border-color : #4682B4;
border-radius: 5px;
font-family: sans-serif;
font-size: 12px;
text-align:center;
}

table tr{
height: 23px;
}


</Style>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

$(function(){
	
    var $btObj = $("input[name=order]");
    
    $btObj.click(function(){
    	console.log("클릭됨");
    	$.ajax({
    		url : "addorder.do",
    		method : "post",
    		success : function(responseData){
    			
    			//var data = responseData.trim();
    			$("article").empty();
    			$("article").html(responseData.trim());
    		}
    	});
    	return false;  	 //end ajax
    });   // end click 
    
    
   $("img").click(function(){
	   $.ajax({
		   url:"productlist.do",
		   success:function(responseData){
			$("article").empty();
   			$("article").html(responseData.trim()); 			   
		   }
	   }); return false; 
   });
    
    
});


</script>

<body>

<!-- el로 바꾸자 -->

<c:choose>
  <c:when test="${empty sessionScope.cart}">
  <img src = "cart.png" style="margin: auto;">
  </c:when>
  
  <c:otherwise>
    <c:set var="cart" value="${sessionScope.cart}"/>
    <h1>장바구니</h1>
    <table width = "500px" class="cartlist">
    <tr id="main"> <td>상품번호</td><td>상품명</td><td>가격</td><td>수량</td> <td>금액</td></tr>
       <c:forEach var="c"  items= "${cart}" > 
           <c:set var="p" value="${c.key}"/>
            <tr>
            <td>${p.prod_no}</td>
            <td>${p.prod_name}</td>
            <td><fmt:formatNumber value="${p.prod_price}" type="currency"/></td>
            <td>${c.value}</td>
            <td>${p.prod_price*c.value}</td>
            </tr> 
       </c:forEach>  
       </table>
       <br>
       <!-- <tr><td> --><input type="button" name="order" value="주문하기"><!-- </td></tr> -->
   
  </c:otherwise>
</c:choose> 


</body>
</html>