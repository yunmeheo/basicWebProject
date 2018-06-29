package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.vo.Product;

public class AddCartHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
				//★★★★★장바구니에 들어오는 상품 세션에 추가만 해주는 서블릿 // 포워딩없음★★★★★
				
				//디비에서 카트에 넣을 정보들 가지고 오자
				String prod_no = request.getParameter("no");
				String prod_name = request.getParameter("name");
				int prod_price = Integer.parseInt(request.getParameter("price"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				
				//세션 가져오기
				HttpSession session = request.getSession();
				
				//상품 셋팅
				Product product = new Product (prod_no,prod_name,prod_price);
				
				//cart 에서 저장되어있는 상품들 가져오기
				HashMap<Product,Integer> cart = (HashMap<Product,Integer>)session.getAttribute("cart");
				
				//카트가 비어있다면 새로운 map 만들어서 새로운 카드객체로 저장하자
				if(cart == null){
					cart = new HashMap();
					session.setAttribute("cart", cart);
				}
				
				//저장되어있는 카트객체에서 상품 가져오기
				Integer inCartQuantity = cart.get(product);
				
				//저장한 ("cart", cart) 를 다시 map에 넣어주자 (해시맵 cart 에 상품정보별 - 수량 저장해야한다)
				// 만약 카트에 같은 상품이 존재한다면 수량만 추가하고, 아니라면 상품.수량 추가한다
				if(inCartQuantity == null){
				}else{
					quantity += inCartQuantity;
				}
				cart.put(product, quantity);		
				System.out.println(quantity);
				
				
				//카트에 ( 상품타입   수량, 이름, 가격 ) 넣은 상품들 다시 빼와서 출력해보자.
				Set<Product> products = cart.keySet();  
				
				//그 상품들을 하나씩 불러온다
				for(Product inCartproduct : products){
					int q = cart.get(inCartproduct);
					System.out.println(inCartproduct.getProd_no()+":" +q);
				}
		
		return null;
	}

}
