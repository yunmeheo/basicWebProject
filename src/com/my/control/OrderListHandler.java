package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.OrderDAOOrcle;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;

public class OrderListHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String info_date = request.getParameter("info_date");
		String info_no = request.getParameter("info_no");
		
		//loginInfo 가지고와서
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("loginInfo");
		String id = c.getId();
		
		session.setAttribute("date", info_date);
		session.setAttribute("info_no", info_no);
		
		//로그인된 아이디에대한 정보를 selectById(id) 로 가져오자
		OrderDAOOrcle orderdao = new OrderDAOOrcle();
		List<OrderInfo> list = new ArrayList<>();
		
		
		// 만약 주문내역이 있을경우에만 고객정보에대한 cart를 가지고온다.
		
			//데이터베이스에서 가지고 온다		
			try {
				list = orderdao.selectById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("list", list);
		
		//리절트 페이지로 패스~~~~
	
		String forwardURL="orderlistresult.jsp";
		System.out.println("총 주문제품 페이지로 넘어갑니다");
		/*RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
		rd.forward(request,response);*/
		
		
		return forwardURL;
	}

}
