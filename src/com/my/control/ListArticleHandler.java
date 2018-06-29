package com.my.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.BoardDAOOracle;
import com.my.vo.Repboard;

public class ListArticleHandler implements Handler{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//public List<Repboard> select(int startRow, int size) throws SQLException{

		BoardDAOOracle bdDao = new BoardDAOOracle();
		//Repboard rb = new Repboard();
		List<Repboard> list = new ArrayList<>();
		//HttpSession session =request.getSession();
		
		
		int page= Integer.parseInt(request.getParameter("page"));
		
		if(page==1){
			
		int startRow;
		int endRow;
		int middleRow;
		int itsFirst = 0;
		
		startRow = itsFirst+1;
		endRow = startRow + 9;
		try {
			list = bdDao.select(startRow, endRow);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		startRow = endRow+1;
		middleRow=endRow+1;
		endRow = middleRow + 9;
		try {
			list = bdDao.select(middleRow, endRow);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}else{
			
			
			
			
		}
		
		
		
		/*//처음들어오는 0		
		int page= Integer.parseInt(request.getParameter("page"));
		String strStartRow =(String)request.getAttribute("startRow");
		Object sw = (Object)session.getAttribute("sw");
		System.out.println("strStartRow"+strStartRow);
		//if(strStartRow !=null)
		System.out.println("sw"+sw);
		System.out.println("들어오는page"+page);
		String nextpage = (String)session.getAttribute("nextpage");
		System.out.println("nextpage:"+nextpage);
		
		//처음아닌아이들
		if((page ==0 && nextpage== null)){
			page++;
			System.out.println("셋팅할page:" + page);
			session.setAttribute("nextpage", "1");	
			request.setAttribute("startRow", page);	
			startRow =page;
			endRow = startRow+9;
			
			try {
				
				list = bdDao.select(startRow, endRow);
				session.setAttribute("startRow", endRow+1);
				System.out.println("endRow:" + endRow);
				session.setAttribute("sw", "1");	
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(sw==null| page >=1){
			startRow =(int)request.getAttribute("startRow");
			endRow = startRow+9;
			System.out.println("증가startRow" +startRow);
			try {
				list = bdDao.select(startRow, endRow);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			
	    //처음
		}
		*/
		
		
		
		
	/*	//startRow = (int)request.getAttribute("startRow");
		//1값을 가져와서 다시 미들값으로 셋팅한다.
		//int middleRow = (int)request.getAttribute("startRow");
		//int 
		
		//만약 가지고온 미들값이 1이라면 = 첫페이지:1부터 10페이지
		if(startRow == 1){
			 
			
			
			
			try {
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//리스트 작성 후 미들값 증가
			middleRow = startRow+10;
			
			//증가한 미들값을 스타트로우에 셋팅한다
			request.setAttribute("startRow", middleRow);
			
		}else if(startRow != 1){
			
			endRow =middleRow+9;
			try {
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}	*/	
			
			
		
			
	request.setAttribute("List", list);
	String forwardURL="repboardlistresult.jsp";
	return forwardURL;	
	}
}
