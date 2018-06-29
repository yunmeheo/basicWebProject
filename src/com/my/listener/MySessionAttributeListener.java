package com.my.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionAttributeListener implements HttpSessionAttributeListener {
	int loginCount = 0;
    public MySessionAttributeListener() {

    
    }

    public void attributeAdded(HttpSessionBindingEvent e)  { 
    String attrName = e.getName();
    	if(attrName.equals("loginInfo")){
    		loginCount++;
    		System.out.println("로그인된 고객수:"+loginCount);
    	}
    
    }

    public void attributeRemoved(HttpSessionBindingEvent e)  { 
    	String attrName = e.getName();
     	if(attrName.equals("loginInfo")){
     		loginCount--;
     		System.out.println("로그인된 고객수:"+loginCount);
     	}
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    }
	
}
