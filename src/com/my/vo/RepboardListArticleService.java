package com.my.vo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.my.dao.BoardDAOOracle;
import com.my.sql.MyConnection;

public class RepboardListArticleService {
	
		private BoardDAOOracle bdDao = new BoardDAOOracle();
		private int size = 10;
		public RepboardArticlePage getArticlePage(int pageNum){
		try(Connection conn= MyConnection.getConnection()){
			int total = bdDao.selectCount(conn);
			List<Repboard> content = bdDao.select((pageNum-1)*size, size);
			return new RepboardArticlePage(total, pageNum, size, content);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	

}
