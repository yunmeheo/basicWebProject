package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.ProductDAOOracle;
import com.my.vo.Product;

public class ProductDetailHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String mime = "text/html;charset=UTF-8";
		response.setContentType(mime); 
		
		
		//public Product selectByNo(String no) throws Exception 
		//들어오는 상품번호로  selectByNo 호출해서 그 값을 다시 돌려보내자
		String no = request.getParameter("no");
		
		ProductDAOOracle dao;
		Product product= new Product();
		
		try {
			dao = new ProductDAOOracle();
			
			if(no != null){
				product = dao.selectByNo(no);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("product", product);
		
		String forwarURL="productdetailresult.jsp";
		
		/*RequestDispatcher rd = request.getRequestDispatcher(forwarURL);
		rd.forward(request,response);*/
		
		return forwarURL;
		
	}

}
