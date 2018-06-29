<%@page import="java.util.List"%>
<%@page import="com.my.vo.Repboard"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>repboarddetailresult.jsp</title>
</head>

<Style>
table td{
width : 200px;
height :500px;
border: 1px dotted;
border-radius: 5px;
font-family: sans-serif;
font-size: small;
}

#main{
border: 1px dotted;
font-family: sans-serif;
font-size: large;
background-color: #F0F8FF;
text-align:center;
}

table{
border: 1px solid;
border-radius: 5px;
border-collapse: collapse;  /* 선들 하나로 합치기 */
text-align:center;
}


</Style>
<body>

<table>
 <tr id="main">
 <td>글번호/ 게시글 제목</td>
 </tr>
 <tr>
 <td>게시글 내용</td>
 </tr>
</table>
</body>
</html> --%>

<%List<Repboard> repboard = (List<Repboard>)request.getAttribute("Content");
int size = repboard.size();
request.setAttribute("detailsize", size);
System.out.println("size:"+size);
int cnt=0;%>

  [{<%for(Repboard repboards : repboard){%>
     "no":<%=repboards.getNo()%>,
     "parent_no":<%=repboards.getParent_no()%>,
     "subject":"<%=repboards.getSubject()%>",
     "content":"<%=repboards.getContent()%>",
     "level":<%=repboards.getLevel()%> 
    <%cnt++;%> 
      <%if(size==0){%> 
      -1
      <%}else if(size>cnt){%>
       },{
       <%}%>
     <%}%>
     }]
      
     
    <%--  <%for(int i=1; i<size-1 ; i++){ %> --%>
    <%--  <% if(size==1){ %>  
          
      <%}else if(size>1){  %>   
      ,
    <%}%> --%>
    <%-- %}%> --%>
      
  
  <%-- <%}%> --%> 


<%-- {"no" :<%=repboard.getNo() %>,
"subject" :"<%=repboard.getSubject() %>",
"content" :"<%=repboard.getContent() %>"
} --%>
<%--  만약에 사이즈가 1이면 출력안하고
      1보다 크면 맨 마지막에만 출력안해--%>







