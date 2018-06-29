package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.Repboard;

public class ReplyRepboardHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		BoardDAOOracle bdDao = new BoardDAOOracle();
		Repboard rb = null;
		
		int replyParent_no = Integer.parseInt(request.getParameter("parent_no"));
		System.out.println(replyParent_no);
		String replyContent = request.getParameter("content");
		String replySubject = request.getParameter("subject");
		String replyPassword = request.getParameter("password");
		request.setAttribute("replyParent_no", replyParent_no);
		
		
		System.out.println("답글달기 준비중 - parent_no :"+replyParent_no+"subject :"+ replyContent+ "content :"+replySubject+"password :"+  replyPassword );
		
		if(!"".equals(replyContent)){
			
			if(!"".equals(replySubject) && !"".equals(replyPassword)){
				//등록성공
				request.setAttribute("msg", "1");
				bdDao.insert(new Repboard(replyParent_no,replyContent,replySubject,replyPassword));
				
			}else{
				
				//내용을 입력 해 주세요.
				request.setAttribute("msg", "-2");
			}
			
		}else{
			//제목을 입력 해 주세요.
			request.setAttribute("msg", "-2");
		}
		
		String forwordURL = "result.do";
		return forwordURL ;
	}

}
