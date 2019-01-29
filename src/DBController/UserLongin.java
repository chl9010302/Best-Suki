package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.UserBean;

public class UserLongin {
	
	Connection conn = null;
	
	/*자바쪽은 DB쪽으로 SQL 전송 , DB는 처리 된 결과를 다시 자바 프로그램에 전달하는 역활을 하는 객체
	PreparedStatement
	*/
	UserBean user;
	ResultSet rs;
	PreparedStatement pstmt;
	
	public UserLongin(UserBean loginuser) {
		loginCheck(loginuser);
	}
	
	
	public int loginCheck(String UserId , String UserPassword) {
		
		user = loginuser;
		
		String selectsql = "SELECT userPassword FROM USER WHERE UserId=?";
	     pstmt = null;
	
		try {
			 conn = application.DBConnection.getDBConection();
			pstmt = conn.prepareStatement(selectsql);
			pstmt.setString(1, UserId);
			rs = pstmt.executeQuery();
			 
			 while(rs.next()) { //결과가 존재한다면
				 if(rs.getString(1).equals(UserPassword)) {
					 return 1; // 로그인성공
					 else
						 return 0;	//비밀번호 불일치
				 }
				 return -1; //아이디가 없음
				 
			 }
			 
	
			
			  		
			  		
			  	      pstmt.executeUpdate();
			  
			conn.close();
			pstmt.close();
			
			
			return true;
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
		return false;
	}

	
	//UserBean bean = new UserBean();
	
	//String UserId = bean.getUserId();
	//String UserPassword = bean.getUserPassword();
	
	
	/*
	public void UserBean(String UserID , String UserPassword) {
		this.UserId1 = UserID;
		this.UserPassword1 = UserPassword;
	} */
	
	
//	public void loginCheck(String UserId , String UserPassword) {
	//	String SQL = "SELECT userPassword FROM USER WHERE userID=?";
		
//		try {
//			if(UserId != null &&  ) {
//				
//			}
//		}catch(Exception e) {
//			System.out.println("계정이나 비밀번호를 확인해주세요.");
//		}
	
		
		

		
	}
	
	
	

}
