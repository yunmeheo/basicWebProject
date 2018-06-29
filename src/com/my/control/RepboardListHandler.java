package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.BoardDAOOracle;
import com.my.vo.Repboard;
import com.my.vo.RepboardArticlePage;
import com.my.vo.RepboardListArticleService;

public class RepboardListHandler implements Handler{

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String mime = "text/html;charset=UTF-8";
		response.setContentType(mime); 
		String msg = "-1";
		

		//디비에서 가져올 준비
		BoardDAOOracle bdDao = new BoardDAOOracle();
		Repboard rb = new Repboard();
		List<Repboard> list = new ArrayList<>();
		List<Repboard> listlist = new ArrayList<>();
		int dbNo = 0;
		String dbSubject =null;
		int  dbParent_no =0;
		
		//파라메터 정보 가져오기
		String searchItem = request.getParameter("searchItem");
		String searchValue = request.getParameter("searchValue");
		System.out.println("검색하기 확인중-searchItem :"+searchItem);
		String forwardURL = null;
			
		int start=0;
		int end = 9;
		
		//★★전체 검색
		if(searchItem==null){
			
			list = bdDao.selectAll();
				
			//페이징하자!!
			String startPage = request.getParameter("startPage");
			String endPage = request.getParameter("endPage");
			
			//시작이 null 아닐경우 = 클릭했을 때
			if(startPage != null){
				start = (Integer.parseInt(startPage))*10-10;
			}
			
			if(endPage != null){
				end = Integer.parseInt(endPage)-1;
			}
			
			System.out.println("시작페이지:" + start);
			System.out.println("끝페이지:" + end);
			
			for(int i = start; i <= end; i++){
				listlist.add(list.get(i));
			}
			
			
			
			
		}else if(searchItem !=null && !"".equals(searchValue)){
		
			
			//★★제목으로 검색
			if("subject".equals(searchItem)){
				
				if("".equals(dbSubject)){
					//잘못입력하면 아무것도 안뜸
				}
				String inputSubject = request.getParameter("searchValue");
				System.out.println("제목검색 확인중-searchValue :"+inputSubject);
				list = bdDao.selectByName(inputSubject);
				
					
					
			//★★번호로 검색		
			}else if("no".equals(searchItem)){
				
				if("".equals(dbSubject)){
					//잘못입력하면 아무것도 안뜸
					msg="-1";
				}
				
				int inputNo = Integer.parseInt(request.getParameter("searchValue"));
				System.out.println("번호검색 확인중-searchValue :"+inputNo);
				list = bdDao.selectByNo(inputNo);
				
			}
		}
		
		int list_size = list.size();
			
		request.setAttribute("listlist", listlist);
		request.setAttribute("list_size", list_size);
		request.setAttribute("List", list);
		request.setAttribute("msg", msg);

		forwardURL="repboardlistresult.jsp";
		return forwardURL;



	}
}
