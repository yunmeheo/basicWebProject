package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.vo.Customer;

public class CustomerDAOOracle implements CustomerDAO {

	public CustomerDAOOracle() throws ClassNotFoundException{
	// 1. 각 자동으로 호출되는 생성자에서 클래스를 JABC 드라이버로드(ojdbc14.jar)로 로드- 미리 한번만 수행해두자.
	// 2. 디비연결 - 디비연결은 오류발생하면 복구가 안되는작업이라 호출할때마다 연결해야 한다.
		//Class.forName("oracle.jdbc.driver.OracleDriver"); 
		
		//context.xml의 driver 로드
		}
	
		
	@Override
	public void insert(Customer c) throws Exception {
		Connection con = null;		
		PreparedStatement pstnt = null;
		ResultSet rs = null;
		// 2. 디비연결 - 디비연결은 오류발생하면 복구가 안되는작업이라 호출할때마다 연결해야 한다.
		con = com.my.sql.MyConnection.getConnection();
		// 3. 송신  rs = stnt.executeQuery(sql);
		String insertsql = "insert into customer (id, password, name) values (?,?,?)";
		try{
		pstnt = con.prepareStatement(insertsql);
		pstnt.setString(1, c.getId());
		pstnt.setString(2, c.getPassword());
		pstnt.setString(3, c.getName());
		pstnt.executeUpdate();
		
		// pk가 오라클에서 중복일경우 오류1번을 의미 함
		}catch(SQLException e){
			int errorCode = e.getErrorCode();
			if(errorCode == 1){
				throw new Exception("이미존재하는 아이디 입니다.");
			}else{
				throw e;
			}
		}
		
		
		// 5. 디비연결닫기
		com.my.sql.MyConnection.close(con,pstnt);
		
		
	}

	@Override
	public List<Customer> selectAll() throws Exception {
		
		Connection con = null;		
		PreparedStatement pstnt = null;
		ResultSet rs = null;
		
		con = com.my.sql.MyConnection.getConnection();
		
		String selecAllSQL = "select * from customer order by id";
		pstnt = con.prepareStatement(selecAllSQL);
		rs = pstnt.executeQuery(selecAllSQL);
		
		
		ArrayList<Customer>list = new ArrayList<>();
		
		while(rs.next()){
			list.add(new Customer(rs.getString("id"), 
								  rs.getString("password"), 
								  rs.getString("name")));	
		}
		com.my.sql.MyConnection.close(con,pstnt,rs);
		return list;
		// 2. 디비연결 - 디비연결은 오류발생하면 복구가 안되는작업이라 호출할때마다 연결해야 한다.
		
	}

	@Override
	public Customer selectById(String id) throws Exception {
		
		Connection con = null;		
		Statement stnt = null;
		ResultSet rs = null;
		// 2. 디비연결 - 디비연결은 오류발생하면 복구가 안되는작업이라 호출할때마다 연결해야 한다.
		con = com.my.sql.MyConnection.getConnection();
		
		//3. SQL구문 송신
		String selectById = "select * from customer where id = ('"+id+"')";
		stnt = con.prepareStatement(selectById);
		rs = stnt.executeQuery(selectById);
		Customer c=null;
		//4. 송신경과 수신
		if(rs.next()){
		  c = new Customer(rs.getString("id"), rs.getString("password"), rs.getString("name"));
		}	
		//5. 접속종료
		com.my.sql.MyConnection.close(con,stnt,rs);
		return c;
		}

	
	
	
	@Override
	public List<Customer> selectByName(String name) throws Exception {
		
		Connection con = null;		
		Statement stnt = null;
		ResultSet rs = null;
		
		// 2. 디비연결 - 디비연결은 오류발생하면 복구가 안되는작업이라 호출할때마다 연결해야 한다.
		con = com.my.sql.MyConnection.getConnection();
		
		//3. SQL구문 송신
		String selectByName = "select * from customer where name = ('"+name+"')";
		stnt = con.createStatement();
		rs = stnt.executeQuery(selectByName);
		
		//4. 송신결과 수신
		
		List<Customer> list = new ArrayList<>();
		while(rs.next()){
			list.add(
			new Customer(rs.getString("id"), rs.getString("password"), rs.getString("name")));
		}
				
		//5. 연결끊기		
		com.my.sql.MyConnection.close(con,stnt,rs);
		return list;
	}
}
