<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%! int sum =0;%>   
<%! double avg =0;%> 
<%! int cnt =0;%>  


<% 
  String scoreValue =  request.getParameter("score");
  sum +=Integer.parseInt(scoreValue);
%>


<% 
//총 참여자수는 ?  버튼이 눌린 횟수일까?
  session.getAttribute("cnt");
  cnt++;
%>


선택한 별점은 <%= scoreValue %> 점 입니다.<br>
별점 총점은 <%= sum %> 점 입니다.<br>
영화 평점은 <%= avg = sum/cnt %> 점 입니다.<br>



</body>
</html>