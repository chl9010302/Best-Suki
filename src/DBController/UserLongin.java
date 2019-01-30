package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBModel.UserBean;

public class UserLongin {
	
	Connection conn = null;
	
	/*자바쪽은 DB쪽으로 SQL 전송 , DB는 처리 된 결과를 다시 자바 프로그램에 전달하는 역활을 하는 객체
	PreparedStatement
	*/
	UserBean user;
	ResultSet rs;
	PreparedStatement pstmt;

	
	public void loginCheck(String UserId , String UserPassword) {
		
		
		String selectsql = "SELECT UserPassword FROM USER WHERE UserId=?";
	     pstmt = null;
	
		try {
			 conn = application.DBConnection.getDBConection();
			 pstmt = conn.prepareStatement(selectsql);
			pstmt.setString(1, UserId);
			rs = pstmt.executeQuery();
			 
			 while(rs.next()) { //결과가 존재한다면
				 if(rs.getString(1).equals(UserPassword)) {
					System.out.println("로그인성공");
				 }
				 else {
					 System.out.println("로그인 실패");
				 }
			 }
						
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
		} catch (SQLException e2) {

			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e3) {

			}
		}
		
	}
}

	

	

	


