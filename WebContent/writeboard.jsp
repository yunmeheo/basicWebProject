<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeboard.jsp</title>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

/* private String content;
private String subject;
private String password; */

$(function(){
	
	var $content = $("input[name=content]");
	var $subject = $("input[name=subject]");
	var $password =$("input[name=password]");
	var $button =  $("input[name=writeBt]");
	
	$button.click(function(){
		
    	$.ajax({
    		url: "writeboard.do",
    		method : "post",
    		data : {"subject" : $subject.val(),
    		    	"content" : $content.val(),
    				"password" : $password.val()
    		},
    		success : function(responseData){
    			var result = responseData.trim(); 
    			
    			console.log("등록성공");
    			var $parentObj = $("article");		
				if($("article").length == 0){
					$parentObj ==  $("body");
				}
				
    			if(result == 1){
    				/* $("div.writebox").hide(); */
    				$("div.btsuccess").show();
    				
    				//$parentObj.empty();
    			}if(result ==-1){
    				alert("내용을 모두 입력해주세요");
    			}
    			//완료되면 화면 지우자
    		}
    		});
    	});return false;
    	
    	
    	//★★완료되었습니다 알림창       
        //리스트로가기
        
        var successListBt = ("input[id=successListBt]");
         $("div.btsuccess input[id=successListBt]").click(function(){
        	 console.log("목록으로 가기 버튼클릭"); 
          $.ajax({
        	  url :"repboardlist.do",
              success:function(responseData){
            	  console.log(responseData); 
                $("article").empty(); 
                $("article").html(responseData.trim()); 
              }
          }); return false;
        }); 
    	
    	
    	
});

</script>



<style>

div.writebox{
width: 500px;
height: 600px;
margin: auto;

}

.writebox form{
padding: 22px;
border: 1px solid;
border-color: #4682B4;
border-radius : 5px;

}


div.btsuccess{display:none;} 

</style>


<body>

<br><br>
 <!-- 글쓰기 테이블 -->
 <div class="writebox">

  <form>
  <input type="text" name ='subject' style="width: 450px" placeholder = "제목을 입력하세요"> <br><br>
  <input type="text" name ='content' style="width: 450px; height :400px" placeholder = "내용을 입력하세요" ><br><br>
  
  <div style="margin: auto; padding :  0px   0px  0px 140px">
  <h5 style="display: inline;  ">비밀번호 : </h5>
  <input  type="password" name ='password'  placeholder = "3자 이내">
  <input  type="button" name="writeBt" value="글올리기"><br>
  <h6 style="margin:0px">비밀번호를 까먹으면 글수정,삭제 안되오니 잊지마세요♥</h6>
  </div>
  
  </form>
 </div>
 
 <%-- div완료 알림창 --%>

  <div class="btsuccess" 
      style= "position:absolute; 
              top:200px; 
              left: 100px;
              background-color: white;
              border-color: #6495ED;
              border-radius: 10px;
              border : 1px solid;
              padding: 10px;
              width : 200px;
              height: 50px;
              ">
    <div style ="background-color:#F0F8FF; padding : 0px">
      <h5 style ="padding : 0px; margin : 0px">정상처리 되었습니다.</h5>
    </div>
   
    <form class="success">
     <input type="button"  value="목록으로" id="successListBt" title="클릭시 목록으로 이동합니다.">
     <!-- <input type="button"  value="쓴글으로" name="successRepBt" title="클릭시 쓴글 확인합니다."> -->
     </form>
  </div>
 
 
 
 
 
</body>
</html>