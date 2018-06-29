<%@page import="com.my.vo.Repboard"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>listArticle.jsp</title>
</head>
<body>



<div class="list" style = "width: 600px ; padding: 0px" >
  <!--  <div style ="border: 1px solid;  display: inline-block; width: 700px"; title="""> -->
  <div style = "height: 30px"></div>
  <table >
  <tr id="main">
  <td style ="width: 80px">게시글번호</td>
  <td style ="width: 80px">상위글번호</td> 
  <td style ="width: 500px">제목</td> 
  <td style ="width: 80px">조회수</td> 
 <!--  <td>글내용 - 안보여야함</td> 
  <td>비밀번호 - 안보여야함</td>  -->
  </tr>  
  
  <c:if test="${articlePage.hasNoArticle()}">
  <tr>
  <td colspan = "4"> 게시글이 없습니다.</td>
  </tr>
  
  
  </c:if>
   <% List<Repboard> list = (List<Repboard>)request.getAttribute("List");
   
   for(Repboard repboard : list){
   
     %> 
     <tr>
       <td><%= repboard.getNo() %></td>
       <%if(repboard.getParent_no()!=0){ %>
       <td> └ <%=repboard.getParent_no()%></td>
       <%} else {%>
         <td><%="" %></td>
         <%}%>
       <td ><a href = ""><%=repboard.getSubject() %></a></td>
       <%--  <td><%=repboard.getContent() %></td>--%>
      <td>비번<%=repboard.getPassword() %></td> 
     </tr>
     <%}%>
     
     <c:if test = "{articlePage.jasArticles()}">
     <tr>
     <td colspan = "4"> 
     <c:if test="{articlePage.startPage >5}">
     <a href="list.do?pageNo=${articlePage.startPage -5}"}>[이전]</a>
     </c:if>
     <c:forEach var="pVo"
     begin="${articlePage.startPage}"
     end ="{articlePage.endPage}">
     <a href ="list.do?pageNo=${pNo}">[${pNo}]</a>
     </c:forEach>
     
     <c:if test ="${articlePage.endPage<articlePage.totalPages}">
     <a href = "list.do?pageNo=${articlePage.startPage+5}">[다음]</a>
     </c:if>
     </td>
     </tr>
     </c:if>
  </table>  
 
  <form style = "padding: 10px" >
  <input type="button"  id="writeReboard" value="글쓰기">
  </form>
</div>  


</body>
</html>