<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>repcheckpassword.jsp</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
  var $commit = $("input[id=commit]");
  var $writeReboard = $("input[id =writeReboard]");  
	$(function(){
		//확인버튼 클릭이벤트 >> 비번확인으로 가야지
		
     	 $commit.click(function(){
     		var $password =$("input[id=password]");
     		
     		console.log("비밀번호"+$password);
             
        	 $.ajax({url :"repcheckpassword.do",
              		 mathod: "post", 
               		 data :{'password': $password.val()},
               		 success:function(responseData){
              		 console.log("수정, 삭제하러 비밀번호 확인하러 가요");
                }
            }); return false; 
          }); 
      
      
      /* //삭제하기
      $writeReboard.click(function(){
    	  
          $.ajax({
            url :"repcheckpassword.do",
          mathod: "post", 
          data :{'no' :no,'password':password},
          success:function(responseData){
        	  
        	  var result = responseData.trim();
              
              if(result == -1){
              alert("삭제되었습니다.");
              	
              }
        	 
           $parentObj.empty();
           }
        });return false;
      });  // end  */
      
	}); //end function   
      </script>

<style>

.chckpassword{
border :1px solid ;
border-radius : 5px;
margin : auto;
width : 300px; 
height: 200px ;
padding :30px   30px;
box-sizing: border-box;
}

<div class="chckpassword">
 <div style ="background-color:#F0F8FF; padding : !important;;">

  <h5>비밀번호를 확인 해 주세요</h5>
 </div>
 <div>
  <form method = "post">
   <input type="password"  id="password" value="password">
   <input type="button"  id="commit" value="확인">
  </form>
 </div> 
 </div>
</html>