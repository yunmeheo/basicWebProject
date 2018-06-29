package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.BoardDAOOracle;
import com.my.vo.Repboard;

public class RepcheckPasswordHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

			
		//삭제인지 수정인지 선택한 값을 가지고 오자
		String selectItem = request.getParameter("selectItem");
		
		//파라메터에서 가져온 비밀번호 세션에 저장하기
		String password = request.getParameter("password");
		int no = Integer.parseInt(request.getParameter("no"));		
		

		//세션에 저장되어있던 번호와 비번
		System.out.println("비밀번호 확인중 - no:"+no);
		System.out.println("비밀번호 확인중 - password:"+password);
		
		//디비에서 비번을 확인해보자
		BoardDAOOracle bdDao = new BoardDAOOracle();
		Repboard rb = new Repboard();
		boolean checkpw = false;
		String forwardURL=null;
		checkpw = bdDao.chkPassword(no,password);
		if(checkpw){ // 비번확인 성공
			
			
			
			//★★업데이트 
			if("update".equals(selectItem) ){
				//forwardURL= "writeboard.jsp";
				request.setAttribute("msg", "1");
				request.setAttribute("password",password);
				
			}//★★삭제일경우 
			else if("delete".equals(selectItem)){
				
				//삭제완료
				bdDao.delete(no);
				request.setAttribute("msg", "2");
				
			}else if("reply".equals(selectItem)){
				
				request.setAttribute("msg", "3");
				
			}
		}else{ // 비번확인 실패
			request.setAttribute("msg", "-1");
		}
		forwardURL="result.do";
		return forwardURL;
	}

}
