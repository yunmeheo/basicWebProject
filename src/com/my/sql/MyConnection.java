package com.my.sql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class MyConnection {
	public static Connection getConnection() throws SQLException{
	
		
		Context initContext;
		Connection conn = null;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	
	public static void close(Connection con){
		
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection con,Statement stnt){
		
		if(stnt != null){
			try {
				stnt.close();
			} catch (SQLException e) {
			}close(con);
		}
	}


	public static void close(Connection con,Statement stnt,ResultSet rs){
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
			}close(con,stnt);
		}
	}
}
