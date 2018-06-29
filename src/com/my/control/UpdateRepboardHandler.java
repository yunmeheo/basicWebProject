package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;

public class UpdateRepboardHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		//업데이트
		BoardDAOOracle bdDao = new BoardDAOOracle();
		String msg = "-1";
		int no = Integer.parseInt(request.getParameter("no"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String password = (String)request.getParameter("password");
		
		System.out.println("no :"+no+"subject :"+ subject+ "content :"+content+"password :"+  password  );
		
		
		if(subject !=null && content !=null){
		bdDao.update(no, subject, content, password);
		//수정완료
		msg = "1";
		}else if(content == null &&content == null){
		//내용을 입력하세요.	
		msg = "-1";
		}
		
		
		request.setAttribute("msg", msg);
		String forwardURL="result.do";
		return forwardURL;
	}

}
