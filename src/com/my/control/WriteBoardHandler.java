package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.Repboard;

public class WriteBoardHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String mime = "text/html;charset=UTF-8";
		response.setContentType(mime); 
		String msg = "-1";
		//파라메터에서 읽어오기
		String subject =request.getParameter("subject");  //20
		String content =request.getParameter("content");  //100
		String password = request.getParameter("password"); //3
		
		
		//디비에 저장하기
		BoardDAOOracle bdDao = new BoardDAOOracle();
		Repboard rb = new Repboard(subject,content,password);
		
		
		//등록성공확인하기
		if(!"".equals(subject)&&!"".equals(content) &&!"".equals(password)){
				
			bdDao.insert(rb);
			
			msg ="1";
			
		}else{
			msg ="-1";
		}
		
		//String forwordURL = "repboardlistresult.jsp";
	request.setAttribute("msg", msg);	
	String forwordURL = "result.do";	
	return forwordURL;
	}
}
