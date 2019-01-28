package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.UserBean;

public class UserJoin {
	UserBean user;
	Connection conn = null;
	Statement stmt = null;

	public UserJoin(UserBean joinuser) {
		insert(joinuser);
	}
	public boolean insert(UserBean joinuser) {
		String insertsql = "insert into user(UserId, UserPassword, UserName, UserAddress, UserSchoolName, UserAge, UserGender, UserStudentnumber, UserPhone, UserFmphone, Isteacher) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    PreparedStatement pstmt = null;
		user = joinuser;
		try {
			 conn = application.DBConnection.getDBConection();
			 pstmt = conn.prepareStatement(insertsql);
	
			
			  			pstmt.setString(1,user.getUserId() );
			  			pstmt.setString(2,user.getUserPassword()); 
			  			pstmt.setString(3, user.getUserName()); 
			  			pstmt.setString(4, user.getUserAddress()); 
			  			pstmt.setString(5, user.getUserSchoolName()); 
			  			pstmt.setString(6, user.getUserAge()); 
			  			pstmt.setString(7, user.getUserGender()); 
			  			pstmt.setString(8, user.getUserStudentnumber()); 
			  			pstmt.setString(9, user.getUserPhone()); 
			  			pstmt.setString(10, user.getUserFmphone()); 
			  			pstmt.setString(11, user.getIsteacher()); 
			  		
			  	      pstmt.executeUpdate();
			  
			conn.close();
			pstmt.close();
			System.out.println(user.getUserSchoolName());
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
