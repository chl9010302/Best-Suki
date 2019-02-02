package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserLongin {
	
	Connection conn = null;
	ResultSet rs;
	PreparedStatement pstmt;
	String sql;
	
	public int loginCheck(String user_id, String user_pw) {
		int i = 0;
		sql = "SELECT USERID FROM USER WHERE USERID = ? AND USERPASSWORD = ?";
		try {
			conn = application.DBConnection.getDBConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 if(rs.getString("USERID") != null) {
					 sql = "UPDATE USER SET USERLOGINSESSION = 1 WHERE USERID = ?";
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setString(1, user_id);
					 i = pstmt.executeUpdate();
					 if(i == 1) 
						i = user_log(user_id);
					 else {
					 }
				 }
			 }
						
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();				
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
			
		}
		return i;
	}
	
	private int user_log(String user_id) throws SQLException {
		int i = 0;
		sql = "INSERT INTO USER_LOG(USER_ID, USER_ACTION, WRITE_DATE)VALUES(?,?,now())";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		pstmt.setString(2, "로그인");
		i = pstmt.executeUpdate();
		return i;
	}
	public void logout(String user_id) throws SQLException {
		sql = "UPDATE USER SET USERLOGINSESSION = 0 WHERE USERID = ?";
		conn = application.DBConnection.getDBConection();
		System.out.println("Id2 : " + user_id);
		pstmt.setString(1, user_id);
		pstmt = conn.prepareStatement(sql);
		System.out.println("sql : " + sql);
		try {
			pstmt.executeUpdate(sql);
		}catch(Exception e) { }
	} 
}

	

	

	


