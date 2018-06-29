package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.sql.MyConnection;
import com.my.vo.Repboard;

public class BoardDAOOracle {
	public BoardDAOOracle(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean chkPassword(int no, String password){
		String selectPasswordSQL =
				"SELECT * FROM Repboard WHERE no=? AND password=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectPasswordSQL);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();			
			return rs.next();
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}finally{
			MyConnection.close(con, pstmt, rs);
		}
	}

	public void delete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE Repboard  WHERE no=?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1,  no);
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			MyConnection.close(con, pstmt);
		}
	}

	public void insert(Repboard board){
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSQL=
				"INSERT INTO Repboard(no, parent_no, subject, content, password) "
						+" VALUES ( Repboard_seq.NEXTVAL, ?, ?, ?,  ?)";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, board.getParent_no());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			MyConnection.close(con, pstmt);
		}
	}

	public  List<Repboard> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectALLSQL =
				"SELECT LEVEL,rownum, a.*"
				+" from Repboard a"
				+" start with parent_no=0"
				+" connect by prior no=parent_no"
				+" order siblings by no desc";	
		ArrayList<Repboard> list = new ArrayList<>();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectALLSQL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int level = rs.getInt("LEVEL");
				int no = rs.getInt("no"); //글번호
				int parentNo = rs.getInt("PARENT_NO");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				String password = rs.getString("PASSWORD");
				int clickCnt = rs.getInt("click_cnt");
				
				list.add(new Repboard(level,no, parentNo, subject, content, password, clickCnt));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			MyConnection.close(con, pstmt, rs);
		}
		return list;
	}

	public List<Repboard> selectByNo(int no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectByNoSQL ="SELECT level, rownum, a.*"
				+" from Repboard a"
				+" where level<=5 "
				+" start with no=?"
				+" connect by prior no=parent_no"
				+" order siblings by no desc";
		ArrayList<Repboard> list = new ArrayList<>();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectByNoSQL);
			pstmt.setInt(1, no);			
			rs = pstmt.executeQuery();
			while(rs.next()){//글번호에 해당하는 상세내용
				int board_no = rs.getInt("no"); //글번호
				int parentNo = rs.getInt("PARENT_NO");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				String password = rs.getString("PASSWORD");
				list.add(new Repboard(board_no, parentNo, subject, content, password));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			MyConnection.close(con, pstmt, rs);
		}
		return list;
	}
	
	
	
	
	    //이름으로 검색
	    public List<Repboard> selectByName(String inputSubject){
	    	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectByNameSQL = "select a.* from Repboard a where subject like ('%' || ? || '%')";

		ArrayList<Repboard> list = new ArrayList<>();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectByNameSQL);
			pstmt.setString(1,inputSubject);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){//글번호에 해당하는 상세내용
				int board_no = rs.getInt("no"); //글번호
				int parentNo = rs.getInt("PARENT_NO");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				String password = rs.getString("PASSWORD");
				list.add(new Repboard(board_no, parentNo, subject, content, password));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			MyConnection.close(con, pstmt, rs);
		}
		return list;
	}

	    
	//글번호 10개씩 나눠서 가져오기 ①
	public int selectCount(Connection conn)throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		
		stmt=conn.createStatement();
		rs=stmt.executeQuery("select count(*) from repboard");
			try{
				
			if(rs.next()){
				return rs.getInt(1);
			}
			return 0;
			
			}finally{
			MyConnection.close(conn, stmt);
			}
	}
	
	//글번호 10개씩 나눠서 가져오기 ②
	public List<Repboard> select(int startRow, int size) throws SQLException{
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectByNameSQL = "select *"
				+" from(select rownum r, a.* "              
				+" from (select *from repboard order by no desc)a start with parent_no = 0 "        
				+" connect by prior no = parent_no)"
				+" where r between ? and ? ";

		ArrayList<Repboard> list = new ArrayList<>();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectByNameSQL);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);			
			rs = pstmt.executeQuery();

		
		List<Repboard> result = new ArrayList<>();
		
		while(rs.next()){
			
		result.add(convertArticle(rs));
		}return result;
		}finally{
		MyConnection.close(con, pstmt);
		}
	}
	
	
	
	
	//글번호 10개씩 나눠서 가져오기 ③
	private Repboard convertArticle(ResultSet rs) throws SQLException{
		
		return new Repboard(rs.getInt("NO"), rs.getInt("PARENT_NO"), rs.getString("SUBJECT"),null,null);
	}
	
	
	
	//글번호 10개씩 나눠서 가져오기 ④
	private Date toDate(Timestamp timestamp){
		
			return new Date(timestamp.getTime());
	}
	
	

	
	
	
	public void update(int no, String subject, String content, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer set=new StringBuffer();
		int updateColumnCnt = 0;
		if(!"".equals(subject)){
			set.append("subject='");set.append(subject);set.append("'");
			updateColumnCnt++;
		}
		if(!"".equals(content)){
			if(updateColumnCnt > 0){
				set.append(",");
			}
			set.append("content='");set.append(content);set.append("'");
			updateColumnCnt++;
		}
		if(!"".equals(password)){
			if(updateColumnCnt > 0){
				set.append(",");
			}
			set.append("password='");set.append(password);	set.append("'");
			updateColumnCnt++;
		}
		if(updateColumnCnt == 0){
			return;
		}
		String updateSQL = "UPDATE Repboard SET " + set +" WHERE no='"+ no + "'";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			MyConnection.close(con, pstmt);
		}
	}

}



