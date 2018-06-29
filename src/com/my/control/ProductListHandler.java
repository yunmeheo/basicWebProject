package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.ProductDAOOracle;
import com.my.vo.Product;

public class ProductListHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		//요청전달데이터 searchItem,searchValue 가져오자
				String searchItem = request.getParameter("searchItem");
				String searchValue = request.getParameter("searchValue");
				
				//한글출력가능하도록
				String mime = "text/html;charset=UTF-8";
				response.setContentType(mime); 
				
				// dao.selectAll()호출하기
				List<Product> list = new ArrayList<>();
				
				
				try {
					ProductDAOOracle dao= new ProductDAOOracle();
					Product p = new Product();
					//List<Product> list = dao.selectAll();
					
					//요청전달 데이터가 없거나 있어도 값이 없을경우 전체검색
					if(searchItem==null || searchItem.equals("")||
					   searchValue==null || searchValue.equals("")){
						list = dao.selectAll();
						
					//item이 이름으로 검색이고 사용자가 입력한 값이 있을 때	
						//    <select name = "searchItem">
						//    <input type="search"  name="searchValue" >
						
					// 들어오는 값이 있을때, 그리고 선택을 이름으로 검색 했을 때	
					}else if("name".equals(searchItem) && !"".equals(searchValue)){
						
						list = dao.selectByName(searchValue);
						
						
					}else if("no".equals(searchItem) && !"".equals(searchValue)){
						p = dao.selectByNo(searchValue);
						//리스트타입이 아니기때문에 리스트에 넣어줘야해!
						if(p != null){
						list.add(p);
						} 
					}
					
					//키 : List      값 : list를 넣기
					request.setAttribute("List", list);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}

				String forwarURL="productlistresult.jsp";
				/*RequestDispatcher rd = request.getRequestDispatcher(forwarURL);
				rd.forward(request,response);*/
		
		return forwarURL;
	}

}
