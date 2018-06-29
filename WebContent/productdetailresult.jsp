<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.my.vo.Product" %>
   
<%Product product = (Product)request.getAttribute("product"); %>
  
 <%-- <table>
  <tr>
  <td>상품번호:</td>
  <td><%out.print(product.getProd_no()); %></td>
  </tr>
  <tr>
  <td>이름 :</td>
  <td><%out.print(product.getProd_name()); %></td>
  </tr>
 <tr>
  <td>가격 : </td>
  <td><%out.print(product.getProd_price());%></td>
  </tr>
 </table>
 <form>
 
 수량 : <input type="search" >
 <input type="button" value="장바구니넣기">
</form> --%>



{"no":"<%=product.getProd_no() %>","name":"<%=product.getProd_name() %>","price":<%=product.getProd_price()%>}