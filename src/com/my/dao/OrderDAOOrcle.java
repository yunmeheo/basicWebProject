package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.sql.MyConnection;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

// 주문정보와 처리는 함께하기때문에   orderinfo + orderline 합쳐서 1개로 클래스 만들자.

public class OrderDAOOrcle {

	//주문정보와 상세정보가 담길 수 있도록 매개변수를 선언해준다.
	/**
	 * 
	 * 전체 주문목록을 반환한다.
	 * @param info
	 */




	public void insert(OrderInfo info){

		//같은 커넥션을 사용하기 위해서 통합된 메소드에서 커넥션 해준다.
		Connection con =null;
		try {

			con = MyConnection.getConnection();
			con.setAutoCommit(false);
			insertInfo(info,con);
			insertLine(info,con);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				// insertinfo , insertline 에서 catch 잡지말고 
				// inser로 떠넘겨셔 여기서 한번에 잡자!
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
		}MyConnection.close(con);
	}



	public void insertInfo(OrderInfo info, Connection con) throws SQLException{

		//디비와 연결
		//Connection con =null;

		PreparedStatement pstmt = null;
		String insertInfoSQL =
				" insert into order_info (info_no, info_date, info_id)"
						+" values(order_seq.nextval, sysdate,?)";

		try {

			pstmt = con.prepareStatement(insertInfoSQL);
			//pstmt.set (1, info.getInfo_id());
			pstmt.setString(1, info.getInfo_c().getId());
			pstmt.executeUpdate();

		} finally{
			MyConnection.close(null, pstmt);
		}
	}



	public void insertLine(OrderInfo info,Connection con) throws SQLException{
		//Connection con =null;   같은 커넥션을 쓰려고 insert로 올리자
		PreparedStatement pstmt = null;

		String inserLineSQL = 
				"insert into order_line(line_info_no, line_prod_no, line_quantity)"
						+" values(order_seq.currval, ?, ?)";
		try {
			pstmt = con.prepareStatement(inserLineSQL);
			List<OrderLine> lines = info.getLines();

			for(OrderLine line : lines){	
				//바인드변수 라파메터  (바인드변수 = ? )
				//pstmt.setString(1, line.getLine_prod_no());
				pstmt.setString(1, line.getLine_prod_no().getProd_no());
				pstmt.setInt(2, line.getLine_quantity());	
				//pstmt.executeUpdate(); 
				//배치처리 후 바인드변수 파라메터를 계속 새로 지정하겠다.
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			pstmt.executeBatch();
		}
		finally{
			MyConnection.close(null, pstmt);
		}
	}

	//전체 주문목록에서 주문건 검색
	/**
	 * 
	 * 아이디별로 주문목록을 검색한다.
	 * @return
	 */
	public List<OrderInfo> selectAll(){

		// 전체내용을 검색해오자. 
		// 가지고온 값이 true라면 false 만날때까지 while문으로 계속 출력한다
		// while문 안에서 하나씩 출력하면서 주문번호를 비교한다 - 같으면 계속출력 같지않다면 1증가해서 다음번호체크?
		// 가지고온 주문번호가 0이 아니라면 
		// 한줄 한줄 돌면서 가지고온 정보를 출력한다.	
		// 가져온 정보중 info_no가 0이 아닐때 결과에서 계속 주문번호를 가져와라
		// 같은 주문번호일경우 기본정보는 1번만
		// while에서는 계속 상세정보를 객체화 시키는 것
		// 주문번호와 이전주문번호가 다를경우에만 orderinfo객체를 만들어주면 된다.
		// System.out.println("주문번호="+info_no);
		// list.add(new 객체이름 (rs.getString 해온 모든열)   return list
		// return orderInfoList;

		Connection con =null;
		ResultSet rs = null;
		Statement stmt = null;
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		List<OrderLine> orderLineList = new ArrayList<OrderLine>();          // 넣을line 타입 list 생성


		String selectAll = "select info_no 주문번호, info_id 주문자아이디,NAME 주문자이름, info_date 주문일자, line_prod_no 상품번호,PROD_NAME 상품명, PROD_PRICE 가격, line_quantity 수량"
				+" from order_info info join customer c"
				+" on info.INFO_ID = c.id left outer join order_line line "
				+" on info.info_no = line.line_info_no left outer join product prod"
				+" on line.LINE_PROD_NO = prod.PROD_NO "
				+" order by 주문번호, 주문자아이디";

		try {
			//커넥션
			con=MyConnection.getConnection();
			stmt = con.createStatement();
			int info_cnt = 0;
			int line_cnt = 0;
			rs = stmt.executeQuery(selectAll);
			//List<OrderInfo> orderInfoList = null;   // info타입리스트  info_c
			//List<OrderLine> orderLineList = null;   // line타입리스트  Product line_prod_no;

			while(rs.next()){
				OrderInfo orderInfo = new OrderInfo(); //한바퀴 돌때마다 info 객체생성
				OrderLine orderLine = new OrderLine(); // OrderLine 객체생성
				if(info_cnt != rs.getInt(1)){           //만약주문번호가 받아온 주문번호와 같다면
					info_cnt = rs.getInt(1);			//현재 주문번호

					//OrderInfo를 셋팅한다.
					orderInfo.setInfo_no(rs.getInt(1));
					orderInfo.setInfo_date(rs.getDate(4));
					orderInfo.setInfo_c(new Customer(rs.getString(2), rs.getString(3),null));
					orderLine.setLine_info_no(orderInfo.getInfo_no());
					orderLine.setLine_quantity(rs.getInt(8));

					//OrderInfo에 넣을 Orderlines를 셋팅한다.
					//if(info_cnt == orderLine.getLine_info_no())
					Product product = new Product();
					do {

						//상품을 OrderLine 객체 안에 넣는다.

						//product.setProd_no(rs.getString("line_prod_no"));
						product.setProd_name(rs.getString(6));
						product.setProd_price(rs.getInt(7));
						orderLine.setLine_prod_no(new Product(rs.getString(5), rs.getString(6), rs.getInt(7)));
						orderLineList.add(orderLine);

						line_cnt = orderLine.getLine_info_no();     //OrderLine 리스트를 생성하던 중 가지고 있는 주문번호
						System.out.print(info_cnt + " " + line_cnt + " " + rs.getString(5) +  "\n");

					} while(info_cnt != line_cnt);//현재 rs 주문번호와 OrderLine 리스트의 주문번호가 같은 동안에만 목록을 만들어 준다.
					//OrderInfo의 OrderLine을 셋팅한다.
					orderInfo.setLines(orderLineList);              // setLine
				}
				orderInfoList.add(orderInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MyConnection.close(con, stmt, rs);
		}

		return orderInfoList;

	}


	/*
	info_no = rs.getInt("info_no");
	info.setInfo_no(info_no);
	Customer info_c = new Customer(rs.getString("info_id"), null,null);
	info.setInfo_c(info_c);
	info.setInfo_date(rs.getDate("info_date"));


	OrderLine line = new OrderLine();
	line.setLine_info_no(info_no);
	Product line_p = new Product(rs.getString("line_prod_no"),rs.getString("prod_name"),rs.getInt("prod_price"));
	line.setLine_prod_no(line_p);
	 */



	// 전체주문건 중 주문목록에서 고객별 검색
	/**
	 * 
	 * 주문자에 해당목록을 반환한다.
	 * @param id 주문자ID
	 * @return
	 * @throws SQLException 
	 */
	public List<OrderInfo> selectById(String id) throws Exception{
		Connection con = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = com.my.sql.MyConnection.getConnection();

		String selectByid = "select info_no , info_id ,NAME , info_date , line_prod_no ,PROD_NAME , PROD_PRICE , line_quantity"
				+" from order_info info join customer c"
				+" on info.INFO_ID = c.id left outer join order_line line "
				+" on info.info_no = line.line_info_no left outer join product prod"
				+" on line.LINE_PROD_NO = prod.PROD_NO"
				+" where info_id  = ? "
				+" order by info_no , info_id ";
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		try{
			pstmt = con.prepareStatement(selectByid);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery( );		
			List<OrderLine> orderLineList = null;
			int before_info_no=0;
			OrderLine line = null;
			OrderInfo info = null;
	
			// 가지고온 리스트를 새로운 객체에 넣어주자
			
			while(rs.next()){		
				
				// 가지고 올때마다 이전번호를 넣어줘야지.
				int info_no = rs.getInt("info_no");
				System.out.println("주문번호:"+ info_no);
				// 만약 가지고온 주문번호가 다르다면 전체 셋팅해주기
				if(before_info_no !=info_no ){
	
					//객체생성해주기
					info = new OrderInfo();
					
					//데이터 넣어주기
					info.setInfo_date(rs.getDate("info_date"));
					info.setInfo_no(info_no);	
	
					//라인객체만들고
					orderLineList = new ArrayList<OrderLine>(); 
	
					//빈 info객체에 만들어놓은 데이터 넣어주기
					info.setLines(orderLineList);
					
					//객체를 리스트에 넣어주기
					orderInfoList.add(info);
					
					//주문번호 초기화
					before_info_no = info_no;
					
	
				}  // end if
				
				//라인 객체생성
				line = new OrderLine();
				
				// 라인 데이터 셋팅
				line.setLine_info_no(rs.getInt("info_no"));
				line.setLine_prod_no(new Product(rs.getString("line_prod_no"), rs.getString("PROD_PRICE"),rs.getInt("line_quantity")));
				line.setLine_quantity(rs.getInt("line_quantity"));	
				
				// 객체를 리스트에 넣어주기
				orderLineList.add(line);	
				
				// 오더라인리스트를   인포리스트에 넣어줘서   그 인포리스트를 다시 리스트에 넣어주는구조다
				//  orderLineList.add(line)  >>  info.setLines(orderLineList)  >>  orderInfoList.add(info) 
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyConnection.close(con, pstmt, rs);
		}
		return orderInfoList; 

	}




	/**
	 * 
	 * 주문번호에 해당 주문정보를 반환한다.
	 * @param info_no 주문번호
	 * @return
	 */
	public OrderInfo infoNo(int info_no){

		return null;

	}


	/**
	 * 
	 * 상품에 해당 주문목록을 반환한다.
	 * @param prod_no 상품번호
	 * @return
	 */
	public List<OrderInfo> selectByProdoNO(String prod_no){

		return null;

	}



	//시작하는 날부터 to날까지의 주문목록 반환한다.
	/**
	 * 
	 * 주문일자에 해당 주문목록을 반환한다.
	 * @param frDate 주문시작일자
	 * @param toDate 주문종료일자
	 * @return
	 */
	public List<OrderInfo> selectByDate(String frDate, String toDate){
		return null;

	}

}
