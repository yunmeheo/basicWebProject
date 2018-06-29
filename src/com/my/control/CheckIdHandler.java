package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.CustomerDAOOracle;
import com.my.vo.Customer;

public class CheckIdHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		String id = request.getParameter("id");
		String msg = "1";
		
		CustomerDAOOracle dao;
		try {
			dao = new CustomerDAOOracle();
			Customer customer;
			try {
				customer = dao.selectById(id);
				String dbId = null;
				
				// 가지고온 데이터가 있을경우에만 id를 가져와서 비교한다.
				if(customer!=null){
					dbId = customer.getId();
			
					if(dbId.equals(id)){
					msg = "-1";
					}else{
						msg = "1";	
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//다른 do리퀘스트에 가지고온 값 저장
	
		request.setAttribute("msg", msg);
		String forwardURL="result.do";
		/*RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		rd.forward(request,response);*/
		
		
		return forwardURL;
	}

}
