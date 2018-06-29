package com.my.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//★★★객체 생성시 한번만 읽어오면 되는 것들은 init으로 옮기자
	public void init() throws ServletException{
		super.init();
		String fileName = "handler.properties";
		String fileRealPath = getServletContext().getRealPath(fileName);
		Properties env = new Properties();
		
		//throws로 넘길 수 없다. try catch 로 하자.  >> 왜지????
		try {
			env.load(new FileInputStream(fileRealPath));
			
			//키들을 모두 가져오자 파일들을 로드하는 프로퍼티들의 키들만 가져오는 거다 (/login=)
			Set<Object> keys= env.keySet();
			
			//키 하나씩 가져오자
			ServletContext application = getServletContext();
			for(Object key : keys){
				String propertyName = (String)key;
				String className=env.getProperty((String)key);
				
				if(className == null){
					System.out.println(className+"가 없습니다.");
				}else{
					//클래스이름이 있을경우 객체생성
					try{
					Class clazz = Class.forName(className); 
					Object obj = clazz.newInstance();        
					Handler handler = (Handler)obj;   
					//String className =env.getProperty(path);
					
					
					// 핸들러를 어디서도 사용할 수 있도록 어트리뷰트로 추가 해 두자.
					application.setAttribute(propertyName, handler);
					System.out.println("이동할 핸들러:"+handler);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getServletPath());	
		String path = request.getServletPath();
		ServletContext application = getServletContext();
		Handler handler = (Handler)application.getAttribute(path);
		
		
		/*if(path.equals("/login.do")){
			handler = new LoginHandler();
		}else if(path.equals("/signup.do")){
			handler = new SignupHandler();
		}else if(path.equals("/result.do")){
			handler = new ResultHandler​();
		}*/
		
		//가져온  String path = request.getServletPath(); 값이 class의 이름이 된다.
		//Searches for the property with the specified key in this property list
		/*com.my.control.LoginHandler
		com.my.control.SignupHandler
		com.my.view.ResultHandler*/
		
		//클래스이름이 없으면 무시
		/*if(className == null){
			System.out.println(path+"처리용 class( "+className+")가 없습니다.");
			return;
		}
		//가져온 클래스이름으로 동적 클래스타입의 객체생성
		try {
			Class clazz = Class.forName(className);  // 클래스로드
			Object obj = clazz.newInstance();        // Creates a new instance of the class represented by this Class object.
			handler = (Handler)obj;                  // 핸들러타입으로 다운캐스팅
			System.out.println("이동할 핸들러:"+handler);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} */
		
		/*
		 * 이닛메서드로 모두 옮기자
		 * 
		 * //if로 모두 가져올 수 없기때문에 프로퍼티스 관리하자
		String fileName = "handler.properties";
		String fileRealPath = getServletContext().getRealPath(fileName);
		//프로퍼티스 객체생성
		Properties env = new Properties();
		//파일의 RealPath 값으로 읽어오자
		env.load(new FileInputStream(fileRealPath));*/
		
		
		
		
		//만약 점프할 url이 없다면 핸들러에있는 url로 이동하면 된다.
		
		if(handler != null){
			String forwardURL = handler.execute(request,response);
			
			if(forwardURL != null){
				RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
				rd.forward(request,response);
			}
		}
	}
}