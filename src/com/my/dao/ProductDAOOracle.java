package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.my.vo.Product;

public class ProductDAOOracle implements ProductDAO {

	
	public ProductDAOOracle() throws ClassNotFoundException{
		
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		}

	@Override
	public void insert(Product p) {
		
	}
	
	@Override
	public List<Product> selectAll() throws Exception{
		
		Connection con = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selecAllSQL = "select * from product order by PROD_NO";	
		con = com.my.sql.MyConnection.getConnection();
		pstmt = con.prepareStatement(selecAllSQL);
		rs = pstmt.executeQuery(selecAllSQL);	
		ArrayList<Product> list = new ArrayList<>();
			while(rs.next()){
				list.add(new Product(rs.getString("PROD_NO"),
									 rs.getString("PROD_NAME"),
									 rs.getInt("PROD_PRICE")));
			}
		com.my.sql.MyConnection.close(con,pstmt,rs);
		return list;
	}

	
	
	@Override
	public Product selectByNo(String no) throws Exception {
		
		Connection con = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectByNo = "select * from product where PROD_NO = ('"+no+"')";
		
		//try {
			
			con = com.my.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectByNo);
			rs = pstmt.executeQuery(selectByNo);
			Product p=null;
			if(rs.next()){
			p= new Product(rs.getString("PROD_NO"), 
						   		   rs.getString("PROD_NAME"), 
						   	   	   rs.getInt("PROD_PRICE"));
			}
			
		/*} catch (SQLException e) {
			e.printStackTrace();
		}finally{*/
		com.my.sql.MyConnection.close(con,pstmt,rs);
		
		//}
	return p;	
	}

	
	
	
	@Override
	public List<Product> selectByName(String word) {
		Connection con = null;		
		Statement stnt = null;
		ResultSet rs = null;
		
		try {
			con = com.my.sql.MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String selectByName = "select * from product where prod_name like('%"+word+"%')";
		//select * from product where prod_name like ('%구%');
		try {
			stnt = con.createStatement();
			rs = stnt.executeQuery(selectByName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Product> list = new ArrayList<>();
		try {
			while(rs.next()){
				list.add(new Product(rs.getString("PROD_NO"), 
									 rs.getString("PROD_NAME"), 
									 rs.getInt("PROD_PRICE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		//5. 연결끊기		
		com.my.sql.MyConnection.close(con,stnt,rs);
		return list;
	}

}
