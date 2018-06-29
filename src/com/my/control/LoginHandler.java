package com.my.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.my.dao.CustomerDAOOracle;
import com.my.vo.Customer;

public class LoginHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String msg = "-1";
		HttpSession session = request.getSession();
		
		/*String chkValue = request.getParameter("c");
		System.out.println("chkValue :" +chkValue);
		
		if(chkValue != null){
			
		Cookie cookie = new Cookie("saveId",id );
		response.addCookie(cookie);
		
		}else{
			Cookie cookie = new Cookie("saveId",id );
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}*/
		
		session.removeAttribute("loginInfo");
		
		//jdbc영역
		CustomerDAOOracle dao;
		try {
			dao = new CustomerDAOOracle();
			Customer customer;
			try {
				customer = dao.selectById(id);
				String dbId = customer.getId();
				String dbpwd = customer.getPassword();
				
				//디비에있는 아이디일때
				if(dbId !=null){
					//가지고온 정보가 있을때
					if(customer != null){
						
						//아이디랑 비번이 잇을경우에만 loginInfo키에 customer 밸류를 세션에 저장해둔다
						if(dbId.equals(id) && (dbpwd.equals(pwd))){		
							
							//로그인 할때마다 이전 Attribute를 지우고 새로 로그인한 Attribute만 기록해두기위해
							session.removeAttribute("loginInfo");
							session.setAttribute("loginInfo",customer);	
							
							msg = "1";
							
						}
					}
				}else{
					return null;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//다른 do리퀘스트에 가지고온 값 저장
		request.setAttribute("msg", msg);
		
		//포워딩하자
		String forwarURL="result.do";
		/*RequestDispatcher rd = request.getRequestDispatcher(forwarURL);
		rd.forward(request,response);*/
		
		
		
		return forwarURL;
	}
		
}
	