<%@page import="java.sql.SQLException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.my.vo.Repboard"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>repboardlistresult.jsp</title>
</head>

<%String selectItem = request.getParameter("selectItem");%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function(){
      var $parentObj = $("article");
    if($parentObj.length == 0){
      $parentObj = $("body");
    }
    
    //★★글쓰기 - 클릭시 writeboard로 이동 
    var $button =$("input[id=writeReboard]");
      $button.click(function(){
        
      
      $.ajax({
        url : 'writeboard.jsp',
        method:'post',
        success:function(responseData){
          console.log(responseData);
          $parentObj.html(responseData);
          console.log("글쓰기버튼 클릭됐어요.")
        }
      }); return false;
    }); 
      
      
    
    //★★수정,삭제,답글 로 넘어가는 확인버튼 
    $("#forwordBt").click(function(){
      var selectValue = $("div.detail select").val();
      
      if(selectValue== "reply"){
        console.log("답글 클릭 완료!");
        
        //★★리플달기 화면은 비번확인 필요없음 변경  
             $("div.detail").find(".subject").html('<input name="subject" style="width: 450px">');
             $("div.detail").find(".content").html('<input name="content" style="width: 500px ; height :500px">');
             $("div.chkpassword").hide();
             $("div.detail").find(".forword").hide();
             $("div.detail").find(".reply").show();
             
             
             
             
             //비밀번호 입력창 보여주기
             $("div.replypassword").show();
        
      }else if(selectValue != "delete"){
        // 만약 삭제버튼일 경우에 
          //${repboard.no} 위칸의 ${repboard.parent_no} 비교해서 같으면 삭제불가
          //아니라면
        $("div.chkpassword").show();
      }else{
        
      $("div.chkpassword").show();
      }
      return false; 
    }); 
    
    
    
    
    
      
    //★★취소버튼 클릭 - 비밀번호창 없애기
    $("input[name=canBt]").click(function(){
      console.log("취소버튼클릭");
      $.ajax({url :"repboardlist.do",
        success:function(responseData){
          
        $("div.chkpassword").hide();
  
        }
      }); return false;
    });
    
    
    
    //★★비밀번호 입력 후 확인버튼 - 클릭하면 repcheckpassword.do 로이동
    $("div.chkpassword form").submit(function(){

      var $password =$("input[name=password]");
          $.ajax({url :"repcheckpassword.do",
                   mathod: "post", 
                   data :{'no': $("div.detail").find(".no").html(),
                     'password': $password.val(),
                     'selectItem':$("div.detail select").val()
                   },
                   success:function(responseData){
                   var result = responseData.trim();
                     
                   // 비번확인 성공시 1번 = ★★업데이트화면으로 바꾸기
                     if(result=='1'){
                     
                    //원래화면의 밸류를 변경해서 text를 변경했고   
                    var subjectValue = $("div.detail").find(".subject").html();
                    var contentValue = $("div.detail").find(".content").html();
                    $("div.detail").find(".subject").html('<input name="subject" style="width: 450px" value="'+subjectValue+'">');
                    $("div.detail").find(".content").html('<input name="content" style="width: 500px ; height :500px" value="'+contentValue+'">');
                    $("div.chkpassword").hide();
                    
                    //작업div를 hide를 시키고   수정완료 버튼을 show시킨다.
                    $("div.detail").find(".forword").hide();
                    $("div.detail").find(".modify").show();
                    
                    // 비번확인 성공시 2번 ★★삭제하기
                     }else if(result =='2'){
                       /* alert("삭제완료되었습니다.");
                        $parentObj.empty(); */
                      $("div.btsuccess").show();
                  
                     }else{ 
                     //비번확인 실패
                    alert("비밀번호를 다시 확인 해 주세요.");
                     }
                } // end success
            }); // end ajax
          return false; 
          });
    
    
    //★★ 업데이트(수정하기) 완료버튼
    $parentObj.on("click","#modifyBt", function(){
    
      var $passwordsubmit = $("div.passwordsubmit");
      var no= $(".no").html();
      var subject = $("input[name=subject]").val(); 
      var content = $("input[name=content]").val();
      var password = $("input[name=password]").val();
      
      $.ajax({
        url: "updaterepboard.do",
        method : "post",
        data :{ "no":no,
            "subject":subject,
            "content":content,
            "password":password
            },
        success:function(responseData){
        var result = responseData.trim();
        if(result == 1 ){
          $("div.btsuccess").show();
        }else if( result == -1){
          alert("내용을 입력 해 주세요♥");
        }
        }
      });$parentObj.off(); return false;
    });
    
    
    //★★ 답글달기 완료버튼
    $parentObj.on("click","#replyBt", function(){
    
      var parent_no= $(".no").html();
      var subject = $("input[name=subject]").val(); 
      var content = $("input[name=content]").val();
      var password = $("input[name=replypassword]").val(); 
      console.log(parent_no+":"+subject+":"+content+":"+password);
      
      $.ajax({
        url: "replyrepboard.do",
        method : "post",
        data :{ "parent_no":parent_no,
            "subject":subject,
            "content":content,
            "password":password
            },
            
        success:function(responseData){
        var result = responseData.trim();
        if(result == 1 ){
          $("div.btsuccess").show();  
                console.log("답글달기 성공");
        }else if( result == -2){
          alert("내용을 입력 해 주세요♥");
        }
        }
      });$parentObj.off(); return false;
    });
    
     
    //★★상세페이지 뿌리기
      var $td = $("table td");
      $("div.list td>a").click(function(){
        console.log("글제목클릭!");
        //상세페이지에서 검색하기 없애기
        var $search = $("form.search");
        var $no = $(this).parent().prev().html();
        $search.hide(); 
        console.log($no);
        $.ajax({
          url:'reborddetail.do',
          method:"post",
          /*data:{'no': $(this).html().trim()}, 
           data:{'no': $divList.find(".listno").html().trim()},*/
           
          data:{'no': $no.trim()}, 
           success:function(responseData){
             
             var result =responseData.trim();
             console.log("상세받아오기 성공!");
             console.log("responseData"+ responseData);
             
             //제이슨파싱
             var jsonObj = JSON.parse(result);
             var $divList = $("div.list");
             var $divDetail = $("div.detail");
            
             $divList.hide();
             var list = $.parseJSON(result);
             var listLen = list.length;
             console.log("총목록길이"+listLen);
             var contentStr = "";
             
             for(var i=0; i<1; i++){
               $divDetail.find(".no").html(jsonObj[i].no);
               $divDetail.find(".subject").html(jsonObj[i].subject);
               $divDetail.find(".content").html(jsonObj[i].content);
               console.log(jsonObj[i]);
             }
              for(var j=1; j<listLen; j++){
                 var tree = $("#tree");
                 var table = $("<table>").appendTo(tree);
            	    //$.each(list, function() {
            	    	

            	        var tr = $("<tr>").appendTo(table);
            	        var td1 = $("<td>").appendTo(tr);
            	        var td2 = $("<td>").appendTo(tr);
            	      
            	            td1.html(jsonObj[j].no);
            	            td2.html(jsonObj[j].content);
            	            td1.css({"width": "50px"});
            	            
            	    //});
               }
               $divDetail.show();
            },
            error:function(xhr, status, error){
            console.log("오류메세지:"+xhr.status);
            }
          }); //end ajax
          return false;
       }); //end a
       
       
       
        
      //★★검색하기  
      $("input[name=searchValueBt]").click(function(){  
        var searchItem =$("div.searchforno select").val();
        var searchValue=$("input[name=searchValue]").val();
        console.log("검색하기 클릭됨: 아이템은"+searchItem+"밸류는"+searchValue);
        $parentObj.empty();
        $.ajax({
          url: "repboardlist.do",
          method :"post",
          data:{
              "searchItem" : searchItem,
            "searchValue" : searchValue
          },
          success : function(responseData){
          var result = responseData.trim();
          console.log("searchItem:"+searchItem+"searchValue:"+searchValue);
          if(result =="-1"){
            alert("한문자 이상 필요합니다.");
            }else if(result =="-2"){
            alert("없는 글번호 입니다.");
            }
            
          $parentObj.empty();   
          $parentObj.html(result);  
          
          }
        });return false;
       });
       
     
   //★★상세페이지 하단의 목록으로가기 버튼
     $("input[id=listBt]").click(function(){
       console.log("클릭되었습니다.");
        $.ajax({url :"repboardlist.do",
        success:function(responseData){
          
        $("article").empty(); 
        $("article").html(responseData.trim()); 
        
        }
      }); return false;
      }); 
       
     
      //★★완료되었습니다 알림창       
      //리스트로가기
       $("input[name=successListBt]").click(function(){
        $.ajax({url :"repboardlist.do",
        success:function(responseData){
          
        $("article").empty(); 
        $("article").html(responseData.trim()); 
        
        }
      }); return false;
      }); 
      
      
      <%-- // ★★조회수 올리기 - 수정중
      $("div.list #clicksubject").click(function(){
        
        //눌릴때마다 카운트를 증가시킨다.
        <%cnt++;%>
      }); --%>
      
      
      //★★페이징
     $("div.paging>a").click(function(){
      console.log("페이징버튼 클릭됐어요.");
     var $startPage = $(this).html().trim();
        $.ajax({
          url : "repboardlist.do",
          data : {"startPage" : $startPage,
                "endPage" : ($startPage*10),    
          },
          success : function(responseData){
            $("article").html(responseData);
          }
        }); return false;
      });
      
      
});
    
   
</script>


<style>




 /* 전체리스트 */
.list{
margin: auto;
}

.list table{
border-radius: 5px;
border: 1px solide;
border-collapse: collapse; 
/* text-align:left; */
}

.list table td{
/* border: 1px dotted; */
border-color : #4682B4;
border-radius: 5px;
font-family: sans-serif;
font-size: 12px;
text-align:center;
}

table tr{
height: 23px;
}

/* 테이블 맨윗줄 상단 스타일 */
#main{
/* border: 1px dotted; */
font-family: sans-serif;
font-size: large;
background-color: #F0F8FF;
text-align:center;
height : 30px;
text-align:center;
}

/* 검색하기 */
div.searchforno{
margin: auto;
padding: 0px  20px  0px   0px ;
display: inline-block; 
}

form.search{
border-radius: 3px;
display:block;
height:25px;
width:500px;
padding: 20px  0px   0px 155px;
}

/* 디테일테이블 */

div.detail {
margin: auto;
display: inline-block;
}   
.detail table{
width: 500px ; 
font-family: sans-serif; 
font-size: small; 
border-collapse: collapse;
text-align:center;
padding : 0px;
}  
 
/* 모든테이블 안에 칸막이 설정*/
table td{
border: 1px dotted;
border-color : #4682B4;
border-radius: 5px;
font-family: sans-serif;
font-size: 12px;
}


div.detail{display:none;}
div.chkpassword{display:none;}
form.modify{display:none;}
form.reply{display:none;}
div.replypassword{display:none;}
div.btsuccess{display:none;} 


</style>

<body>




   
   
   
<%-- 메인페이지 --%>
   
<div class="list" style = "width: 600px ; padding: 0px" >


  <!-- 게시판 메인의 검색창 -->
  
  <div class="searchforno">
   <form class="search">
    <select name = "searchItem" style="height: 21px">
     <option value = "subject" selected>제목으로 검색</option> 
     <option value = "no" selected>번호로 검색</option>
     </select> 
    <input type="search"  name="searchValue" >
    <input type="button"  name="searchValueBt"   value="검색" >
  </form>
  </div>


  <!-- 글쓰기버튼 -->
    <form style = "padding: 10px" >
    <input type="button"  id="writeReboard" value="글쓰기">
    </form>



  <!-- 전체리스트 -->
  <table >
    <tr id="main">
    <td style ="width: 100px; " >게시글번호</td>
    <td style ="width: 500px">제목</td> 
    <td style ="width: 80px">조회수</td> 
    </tr>  
    
    <c:set var="list" value="${requestScope.listlist}"/>
    <c:forEach  var="repboard"  items="${list}" >
     
    <tr>
     <td class="listno">${repboard.no}</td>
     <td style = "text-align:left">
     <c:forEach var="level" begin="1"  end="${repboard.level-1}">
     <img src = "reBt.png">
     </c:forEach><a style ="text-decoration: none"  href = "">${repboard.subject}</a></td>
    
     <td>${repboard.password}/${repboard.click_cnt}/${repboard.parent_no}</td>
   </tr>
  </c:forEach> 
 </table>
 
 <!-- 검색시 리스트 -->
 
   <table >
    <tr id="main">
    <td style ="width: 100px; " >게시글번호</td>
    <td style ="width: 500px">제목</td> 
    <td style ="width: 80px">조회수</td> 
    </tr>  
    
    <c:set var="list" value="${requestScope.list}"/>
    <c:forEach  var="repboard"  items="${list}" >
     
    <tr>
     <td class="listno">${repboard.no}</td>
     <td style = "text-align:left">
     <c:forEach var="level" begin="1"  end="${repboard.level-1}">
     <img src = "reBt.png">
     </c:forEach><a style ="text-decoration: none"  href = "">${repboard.subject}</a></td>
    
     <td>${repboard.password}/${repboard.click_cnt}/${repboard.parent_no}</td>
   </tr>
  </c:forEach> 
 </table>
 

    
     
     <!-- 페이징  -->  

   <div class="paging" style="display: inline-block;  margin: 10px   0px    0px  250px;">
   <c:set var="list_size" value="${requestScope.list_size}"/>
   총글수: ${list_size}  
   <c:set var="pageNum" value="${list_size/10}"/>
   
   <c:if test="${list_size%10==0}">
   필요한 페이지수 : ${list_size/10} 
   <c:forEach var="page" begin="1" end="${pageNum}">
   [<a href = "">${page}</a>]
   </c:forEach> 
   </c:if>
   
   <c:if test="${list_size%10!=0}">
   필요한 페이지수 : ${list_size/10+1} 
   <c:forEach var="page" begin="1" end="${pageNum+1}">
   [<a href = "">${page}</a>]
   </c:forEach> 
   </c:if>
   </div>
   
</div>  
<br><br>


<%-- div상세글 --%>

<div class="detail">


  <table>
  <tr style="background-color: #F0F8FF">
  <td style="width: 50px" >글번호</td>
  <td >제목</td>
  </tr>
  <tr>
  <td class="no">~</td>
  <td class="subject">~</td>
  </tr>
  <tr>
  <td  class="content" colspan="2" style="height : 500px ; padding : 0px">~</td>
  </tr>
  </table>
  <br>
  
  <c:set var="no" value="${sessionScope.no}"/>
   <h5> ${no}번글에 대한 답글 목록 </h5>
  <div id="tree"></div>
  

  
  
  
  
  <form class="forword" style="padding : 10px">
  <h5 style="display: inline; width: 30px"> 작업</h5>
    <select name="selectItem"> 
    <option value="update" >수정 </option> 
    <option value="delete" >삭제 </option> 
    <option value="reply" >답글달기 </option>
    </select> 
   <input type="button" id="forwordBt" value="확인">
   <input type="button" id="listBt" value="목록보기">
  </form>
  
  
  <!-- 생성/삭제 반복되는 버튼들 -->
  
   <form class="modify" style="padding : 10px">
   <input type="submit" id="modifyBt" value="수정하기">
   </form>
   
   <form class="reply" style="padding : 10px">
   <input type="submit" id="replyBt" value="답글달기">
   </form>
   
   
   <%-- 답글쓰기의 하단 비밀번호 입력창 --%>
  <div class ="replypassword" style="margin: auto; padding :  0px   0px  0px 140px">
  <h5 style="display: inline;  ">답글의 비밀번호 : </h5>
  <input  type="password" name ='replypassword'  placeholder = "3자 이내">
  <br>
  <h6 style="margin:0px">비밀번호를 까먹으면 글수정,삭제 안되오니 잊지마시길 바라옵니다♥</h6>
  </div>
   
</div>


<%-- div비밀번호확인창 --%>

<div class="chkpassword" style="position: absolute; 
            top:200px; 
            left: 100px ;
            background-color: white;
            border-radius: 10px;
            border-color: #6495ED;
            border : 1px solid;
            padding: 10px;">
  <div style ="background-color:#F0F8FF; padding : 0px">
    <h5 style ="padding : 0px; margin : 0px">비밀번호를 확인 해 주세요</h5>
  </div>
 
  <form class="passwordsubmit">
   <input type="password"  name="password" >
   <input type="submit"  value="확인" name="comBt">
   <input type="button"  value="취소" name="canBt"  title="취소 클릭시 목록으로 이동합니다.">
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
   <input type="button"  value="목록으로" name="successListBt" title="클릭시 목록으로 이동합니다.">
   <!-- <input type="button"  value="쓴글으로" name="successRepBt" title="클릭시 쓴글 확인합니다."> -->
   </form>
</div>



</body>
</html>