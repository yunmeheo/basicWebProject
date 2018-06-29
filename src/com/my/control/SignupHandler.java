package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.CustomerDAOOracle;
import com.my.vo.Customer;

public class SignupHandler implements Handler{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		//파라메터에서 받아온 데이터 저장하기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String msg = "";
		
		
		//받아온 데이터 DB에 저장하기
		try {
			CustomerDAOOracle dao = new CustomerDAOOracle();
			
			try {
				
				Customer c = new Customer(id, pwd, name);
				c.setId(id);
				c.setPassword(pwd);
				c.setName(name);
				
				//걸어야할 조건 아이디가 3글자보다 클때 '아이디에러메세지'
				if(id.length() > 3){
					msg = "Please input your id below three letters";					
				}else{	
					
				dao.insert(c);
				msg = "1";
				
				}
								
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("msg", msg);
		
		String forwarURL="result.do";
		/*RequestDispatcher rd = request.getRequestDispatcher(forwarURL);
		rd.forward(request,response);*/
		
		return forwarURL;
	}
	
	
	
	
	
	

}
