<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>search.jsp</title>
</head>

<% String item = request.getParameter("searchItem");
  if(item == null){
	item="no";  
  }
  
  
    /* String value = request.getParameter("searchValue");
    if(value == null){
    value = "";
    } */
%>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script>
  
  	$(function(){
  		$("option[value=<%=item%>]").attr("selected","true");
  		<%-- $("input[name=searchvalue]").val("<%=value%>") --%>
  		$("input[name=searchvalue]").val('${param.searchvalue}')
  		
  		$("form").submit(function(){
  			return false;
  		});
  		
  		
  		$("input[type=button]").click(function(){
  			$.ajax({ url:"productlist.do",
  					method:"post",
  					data:$("form").serialize(),
  					success:function(responseData){
  					//console.log(responseData);
  					
  					
  					//바디부분을 지웠다가 그부분을 결과로 다시 채워보자
  					//var $parentObj = $("body");
  					//var $parentObj = $("article");
  					
  					//$parentObj 를 뭘로할까?   article이 뭔지 모르니까 
  					//바디가 있는지 article이 있는지 모른다. 둘다 할 수 있도록 미리 만들어두자.
  					var $parentObj = $("article");		
  					if($("article").length == 0){
  						$parentObj ==  $("body");
  					}
  					
  					//$parentObj.remove();
  					$parentObj.empty();
  					$parentObj.html(responseData.trim());
  				}
  			});
  			return false;
  		});  // end button
  	});
  </script>

<style>

  form{
  /* border: 1px dotted;
  border-radius: 10px;
  border-color : #4682B4; */
  display:block;  
  height:50px;
  width:360px;
  padding: 10px;
  box-sizing: border-box;
  margin: auto;
  }

</style>

<body>

  <form>
  <select name = "searchItem">
   <option value = "name" selected>이름으로검색</option>
   <option value = "no" selected>번호로검색</option>
  </select >
  <input type="search"  name="searchValue" >
  <input type="button"  value="검색">
  </form>
  
<%--  <%
  RequestDispatcher dispather = request.getRequestDispatcher("productlist.do");
  dispather.include(request, response);
  %>  --%>

</body>
</html>