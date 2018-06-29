package com.my.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	
    
    public MyServletContextListener() {
    	System.out.println("MyServletContextListener 감시자 객체생성");
    }
    
    //①번 호출
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("contextInitialized() 메서드 호출");
    }
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("contextDestroyed() 메서드 호출");
    }


	
}
