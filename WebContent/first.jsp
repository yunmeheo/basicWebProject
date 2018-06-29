<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  <!-- 페이지 디렉티브 -->
<%@ page import = "java.util.Date" %>    
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->


<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<title>first.jsp</title>
</head>
<body>
 첫번째 JSP입니다.
 
 <p>
 JSP페이지의 구성요소
 <ul>
  <li>HTML요소</li>
  <li>JSP요소</li>
        <ol>
            <li>scripting요소</li>
               <ul>
                      <li>scriptlet : 자바구문 작성가능 : 
                                      .java파일의 _javaService()내부에 포함될 구문  >> 
                        <%int i=10; %>  
                        <%out.print(i); %> 
                      </li>
                      <li>expression : 출력 : scriptl과 차이점은 출력만을 위함 
                                      .java파일의 _javaService()내부에 포함될 구문  >>  
                        <%=i%>
                      </li>
                      <li>declaration : 인스턴스변수, 메서드선언 -
                      .java 파일의 _jspService()외부에 포함될 구문</li>
                      
                       <%!int i; %>  
                       
                       <%!void m(){
                    	  // out.print(i); m()변수는 out변수를 사용할 수 없다.
                       } %>  
                       <%= this.i %>
                       <%out.print(i); %> 
                       
                    </ul>
            <li>directive요소</li>
              <ul>
                <li>page directive : 페이지지시자 :최상단에 선언 되어있다. 
                                    .java파일에 generated할때 필요한 정보를 설정한다.
                                    속성: contentType - 응답형식지정, 
                                    import-  자바파일이 아니기 때문에 import 영역이 명확하지 않기때문에 사용, 
                                    buffer-응답출력스트림의 내부버퍼크기설정 : 기본 8Kb, 
                                    isErrorPage-예외처리전용페이지 : 기본 false, 
                                    errorPage-현재페이지에서 예외가 발생하면 자동포워드될 페이지를 설정   
                                    <%Date dt = new Date(); %>
                </li>
                <li> include directive : 다른페이지를 포함할 때, </li>
                <li> taglib directive</li>
              </ul>
            <!-- include 태그가 두개가 있네? -->
            <li>action tag요소</li>
              <ul>
                <li>stantard tag</li>
                  <ul>
                    <li>include tag : 페이지포함</li>
                    <li>forward tag : vpdlwldlehd - RequestDispatcher 의 forward()</li>
                    <li>useBean tag</li>
                    <li>setPropetie tag</li>
                    <li>getPropetie tag</li>
                  </ul>
                
                
                <li>include tag</li>
                <li>custom tag</li>
              </ul>
            <li>implicit object - .java파일의 _jspService()내부의 
            미리선언되어있는 매개변수,지역변수
              <ul>
                <li>request</li>
                <li>response</li>
                <li>out : JspWriter</li>
                <li>page: Object</li>
                <li>pageContext: PageContext</li>
                <li>application:ServletContext</li>
                <li>session:HttpSession</li>
              </ul>
            </li>
          </ol>
      </li> 
  </ul>
</body>
</html>