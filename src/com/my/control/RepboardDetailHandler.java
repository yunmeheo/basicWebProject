package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.BoardDAOOracle;
import com.my.vo.Repboard;

public class RepboardDetailHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("no");
		//리스트에서 클릭된 no를 키로 가져온다.
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		session.setAttribute("no", no);


		//객체생성
		BoardDAOOracle bdDao = new BoardDAOOracle();
		
		//가져온 키로 selectByNo 실행
		List<Repboard> content = new ArrayList<>();
		content = bdDao.selectByNo(no);
		
		//리스트에 저장하기
		request.setAttribute("Content", content);
		
		//포워딩
		System.out.println("제목:"+no+"로찾아올거야");
		
		String forwardURL = "repboarddetailresult.jsp";
		return forwardURL;
		
	}

}
