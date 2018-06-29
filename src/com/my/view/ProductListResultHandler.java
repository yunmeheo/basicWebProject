package com.my.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.control.Handler;
import com.my.vo.Product;

public class ProductListResultHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		//setAttribute 해놓은 자료 꺼내오기
				List<Product> list = (List<Product>)request.getAttribute("List");
				
				
				//한글출력
				String mime = "text/html;charset=UTF-8";
				response.setContentType(mime);
				
				//출력
				PrintWriter out = response.getWriter();
				
					out.print("<html>");
					out.print("<body>");
					out.print(" <table>");
				for(Product product:list){
					
					
					out.print("<tr>");
					out.print("<td >");out.print(product.getProd_no());out.print("</td>");
					out.print("<td>");out.print(product.getProd_name());out.print("</td>");
					out.print("<td>");out.print(product.getProd_price()); out.print("</td>");
					out.print("</tr>");
					
				  
				}
				  out.print("</table>");
				  out.print("</body>");
				  out.print("</html>");
				
				
		
		
		
		return null;
	}

}
