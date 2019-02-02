package DBController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.UserBean;
import usingstaticfunction.DBConnectionKeeping;

public class UserDataUpdate {
	public UserDataUpdate(UserBean userbean, String User_id) {

		UserUpdate(userbean, User_id);
	}

	public boolean UserUpdate(UserBean userbean, String User_id) {
		DBConnectionKeeping dbConnectionKeeping;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		
		Statement stmt = null;
		
		try {
		Connection con = usingstaticfunction.DBConnectionKeeping.con;
		
		stmt = con.createStatement();
		StringBuilder sb = new StringBuilder();
		String sql = sb.append("UPDATE USER SET")
				.append(" UserId = '"+userbean.getUserId())
				.append("', UserPassword = '"+userbean.getUserPassword())
				.append("', UserName = '"+userbean.getUserName())
				.append("', UserAddress = '"+userbean.getUserAddress())
				.append("', UserSchoolName = '"+userbean.getUserSchoolName())
				.append("', UserAge = '"+userbean.getUserAge())
				.append("', UserGender = '"+userbean.getUserGender())
				.append("', UserPhone = '"+userbean.getUserPhone())
				.append("', UserFmphone = '"+userbean.getUserFmphone())
				.append("' where UserId = '")
				.append(User_id)
				.append("';").toString();
		System.out.println(sql);
		
			stmt.executeUpdate(sql);
			System.out.println("Success update");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return false;
	}
}
