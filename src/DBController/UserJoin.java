package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBModel.UserBean;

public class UserJoin {
	private UserBean userbean;
	private Connection conn = null;
	private PreparedStatement pstmt;
	private String sql;
	private ResultSet rs;
	private int join_checksession;
	public UserJoin() { }
	public UserJoin(UserBean userjoin) {
		insert(userjoin);
	}
	public boolean insert(UserBean userjoin) {
		sql = "INSERT INTO "+config.StaticProperty.getuser_tb()+"(USER_ID_PK, USER_PASSWORD, USER_NAME, USER_ADDRESS, USER_SCHOOLNAME, USER_AGE, USER_GENDER, USER_PHONE, USER_FMPHONE, USER_TEACHERSESSION, USER_LOGINSESSION) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    PreparedStatement pstmt = null;
	    userbean = userjoin;
		try {
			 conn = application.DBConnection.getDBConection();
			 pstmt = conn.prepareStatement(sql);
	  			pstmt.setString(1,userbean.getUSER_ID_PK() );
	  			pstmt.setString(2,userbean.getUSER_PASSWORD()); 
	  			pstmt.setString(3, userbean.getUSER_NAME()); 
	  			pstmt.setString(4, userbean.getUSER_ADDRESS()); 
	  			pstmt.setString(5, userbean.getUSER_SCHOOLNAME()); 
	  			pstmt.setString(6, userbean.getUSER_AGE()); 
	  			pstmt.setString(7, userbean.getUSER_GENDER()); 
	  			pstmt.setString(8, userbean.getUSER_PHONE()); 
	  			pstmt.setString(9, userbean.getUSER_FMPHONE()); 
	  			pstmt.setString(10, userbean.getUSER_LOGINSESSION());
	  			pstmt.setString(11, userbean.getUSER_TEACHERSESSION()); 
	  	        pstmt.executeUpdate();
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {  e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {	}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e3) {	}
		}
		return false;
	}
	public int joinCheck(String user_id) {
		join_checksession = 1;
		sql = "SELECT USER_ID_PK FROM "+config.StaticProperty.getuser_tb()+" WHERE USER_ID_PK = ? ";
		rs = null;
		try {
			conn = application.DBConnection.getDBConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(rs.getString("USER_ID_PK") != null) {
					join_checksession = 0;
				}
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e2) {  }
		} return join_checksession;
	}
}
