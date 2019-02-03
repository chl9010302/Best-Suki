package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.UserBean;

public class UserJoin {
	UserBean userbean;
	Connection conn = null;
	Statement stmt = null;

	public UserJoin(UserBean userjoin) {
		insert(userjoin);
	}
	
	public boolean insert(UserBean userjoin) {
		String insertsql = "INSERT INTO USER_TB(USER_ID_PK, USER_PASSWORD, USER_NAME, USER_ADDRESS, USER_SCHOOLNAME, USER_AGE, USER_GENDER, USER_PHONE, USER_FMPHONE, USER_TEACHERSESSION) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    PreparedStatement pstmt = null;
	    userbean = userjoin;
		try {
			 conn = application.DBConnection.getDBConection();
			 pstmt = conn.prepareStatement(insertsql);
			  			pstmt.setString(1,userbean.getUSER_ID_PK() );
			  			pstmt.setString(2,userbean.getUSER_PASSWORD()); 
			  			pstmt.setString(3, userbean.getUSER_NAME()); 
			  			pstmt.setString(4, userbean.getUSER_ADDRESS()); 
			  			pstmt.setString(5, userbean.getUSER_SCHOOLNAME()); 
			  			pstmt.setString(6, userbean.getUSER_AGE()); 
			  			pstmt.setString(7, userbean.getUSER_GENDER()); 
			  			pstmt.setString(8, userbean.getUSER_PHONE()); 
			  			pstmt.setString(9, userbean.getUSER_FMPHONE()); 
			  			pstmt.setString(10, userbean.getUSER_TEACHERSESSION()); 
			  		
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
}
