<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>   
<%@ page import="com.my.vo.Product" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlistresult.jsp</title>
</head>


<Style>

table td{
border: 1px dotted;
border-color : #4682B4;
border-radius: 5px;
font-family: sans-serif;
font-size: 12px;
}

/* 테이블 맨윗줄 상단 스타일 */
#main{
font-family: sans-serif;
font-size: large;
background-color: #F0F8FF;
text-align:center;
height : 30px;
text-align:center;
}

table{
width: 500px ; 
font-family: sans-serif; 
font-size: small; 
border-collapse: collapse;
text-align:center;
padding : 0px;
margin: auto;
} 

/* 모든테이블의 높이 */
table tr{
height: 23px;
}

div.detail{
border-radius: 5px;
border: 1px solide;
border-collapse: collapse; 
}

</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

	// 테이블의 a태그 (상품번호)가 클릭되면 상품 상세페이지로 넘겨주자.
	$(function(){
		var $parentObj = $("article");
		
		if($parentObj.length == 0){
			$parentObj = $("body");
		}
		
		/* 출력되지 않는다 왜냐하면 $parentObj.empty(); 후 돔트리가 생성되기 전이라서
		$("#btcart").click(function(){
			alert("장바구니가 클릭되었습니다.")
		}); */
		//지금 존재하는 객체의. 하위객체가 생성되면 click이벤트를 실행하라
		
		
		$parentObj.on("click", "#btCart", function(){
			
			var no=$("#no").html();    // no를 찾아서 값을 가져오자. 왜 html로 가져오지
			var name=$("#name").html();
			var price=$("#price").html();
			var quantity = $("input[name=quantity]").val(); // 돔트리에서 quantity를 찾아서 값을 가져오자. 입력값이기 때문에 input태그는 val로 가져온다
			console.log(no+","+name+","+price+","+quantity);
			
			$.ajax({
				url :"addcart.do",
				mathod: "post",	
				data :{'no' :no,'name':name,'price':price,'quantity':quantity },
				success:function(responseData){
							
					alert("장바구니에 추가되었습니다.");
					$parentObj.empty();	
				} // end success
			}); $parentObj.off(); return false; // end ajax
			//아티클영역에 있는 on 메서드의 이벤트를 계속 초기화 시켜줘야한다.
			
			
		});  // end button
		
		
		var $td = $("table td");		
		$("table td>a").click(function(){
			//a가 클릭되었을때 article비우고 결과 보여주자
			$.ajax({
				url:'productdetail.do',
				method:"post",
				data:{'no': $(this).html().trim()},
				success:function(responseData){
					result = responseData.trim();
					//console.log(responseData);
					
					/* var $parentObj = $("article");
					if($parentObj.length == 0){
						$parentObj = $("body");
					} */
					
					//제이슨pasing 해야한다.
					$parentObj.empty();
				
						
						jsonObj = JSON.parse(result);
						console.log(jsonObj.no + "," +jsonObj.name+","+jsonObj.price);
						var data = '<h1> 상품 상세정보 </h1>'
						
						data += '<table class="detail">';
						
						data += '<tr>'; //
						data += '<td>상품번호 :';
						data += '</td>';
						data += '<td id="no">'
						data += jsonObj.no;
						data += '</td>';
						data += '</tr>'; //
						
						
						
						data += '<tr>'; //
						data += '<td >상품이름 :';
						data += '</td>';
						data += '<td id="name">'
						data += jsonObj.name;
						data += '</td>';
						
						data += '</tr>'; //
						
						data += '<tr>'; //
						
						data += '<td>상품가격 :';
						data += '</td>';
						
						data += '<td id="price">'
						data += jsonObj.price;
						data += '</td>';
											
						data += '</tr>';  //
						
						data += '</table>';
						
						data += '<form>';
						data += '수량:';
						data += '<input type="number" min="1" value="1" name="quantity" >'
						data += '<br>'
						data += '';
						data += ' <input type="button" id="btCart" value="장바구니넣기">'
						data += '</form>';
						
						$parentObj.html(data);
				},
				error:function(xhr, status, error){
					console.log(xhr, status);
				}
			});	
		return false;
	});
});
</script>


<body>

<div style ="height: 50px;margin: auto; padding: 10px"   >
<jsp:include page = "search.jsp"></jsp:include>
</div>


<table>
  <tr id="main">
    <td>상품번호</td>  
    <td>상품이름</td> 
    <td>상품가격</td>
   </tr> 
   
      <%
    List<Product> list = (List<Product>)request.getAttribute("List");
    for(Product product:list){%>
    <tr>
    <td><a href = "#"><%out.print(product.getProd_no()); %></a></td> 
    <%-- <a href ="productdetail.do?no=<%=product.getProd_no()%>>"></a> --%>
    <td><%out.print(product.getProd_name()); %></td> 
    <td><%out.print(product.getProd_price()); %></td> 
    </tr>  
   <% }  
      %>
      
</table>
  <!-- 받아온리스트를 jsp를 이용해서 출력할거야. -->

</body>
</html>
















