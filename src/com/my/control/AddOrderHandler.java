package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.OrderDAOOrcle;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class AddOrderHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		



		//★★★★ 주문하기 버튼 눌렀을때 처리하자★★★★ 

		//세션 가져오기
		HttpSession session = request.getSession();

		//세션에 저장된 커스터머정보 가져오기
		Customer c = (Customer)session.getAttribute("loginInfo"); 

		//커스터머의 주문내역 가져오기
		HashMap<Product, Integer> cart = (HashMap<Product, Integer>)session.getAttribute("cart");

		String forwardURL = null;
		int quantity = 0;

		// 장바구니에 상품 있을경우에만 
		if(cart != null){

			//세션이 없다면 loginform 페이지로 포워딩  
			if(c== null){

				//포워드시킬 주소
				forwardURL = "loginform.jsp";

				System.out.println("로그인 안되어있어요, 로그인 해쥬세염");

			}

			// 세션이 있다면 dao.insert 시키고 인설트 후 주문목록으로 포워딩	

			else if(c!=null){

				OrderDAOOrcle dao = new OrderDAOOrcle();

				//cart의 키를 가져온다. (장바구니에 추가되어있는 product를 모두 가져온다)
				Set<Product> products = cart.keySet();
				List<OrderLine> line = new ArrayList<>();

				//상품의 수량과 상품목록을 가져오자
				for(Product p : products){
					quantity = cart.get(p);

					//리스트에 셋팅
					line.add(new OrderLine(0, p, quantity));

					//info에 상품과 주문상세정보 insert
					OrderInfo info = new OrderInfo(0, null, c, line);
					dao.insert(info);

					//인설트 후 리스트 출력하기위해서 포워딩시키기
					forwardURL="orderlist.do";

					//보내고나서는 카트를 지우자
					session.removeAttribute("cart");

					System.out.println("주문완료, 땡큐");
				}
			}

		}else if(cart == null){
			
			//상품이 없는데 계속 주문한다고 하면 ... 장바구니에 물건 넣고 주문하렴...
			System.out.println("카트에 아무것도 없능데??");
			forwardURL="productlistresult.jsp";
			/*RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
			rd.forward(request,response);*/

		}
		/*RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		rd.forward(request,response);*/
		
		return forwardURL;
	}

}
