package DBController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.UserBean;
import usingstaticfunction.DBConnectionKeeping;

public class UserDataUpdate {
	public UserDataUpdate() { }
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
		String sql = sb.append("UPDATE "+config.StaticProperty.getuser_tb()+" SET")
				.append(" USER_ID_PK = '"+userbean.getUSER_ID_PK())
				.append("', USER_PASSWORD = '"+userbean.getUSER_PASSWORD())
				.append("', USER_NAME = '"+userbean.getUSER_NAME())
				.append("', USER_ADDRESS = '"+userbean.getUSER_ADDRESS())
				.append("', USER_SCHOOLNAME = '"+userbean.getUSER_SCHOOLNAME())
				.append("', USER_AGE = '"+userbean.getUSER_AGE())
				.append("', USER_GENDER = '"+userbean.getUSER_GENDER())
				.append("', USER_PHONE = '"+userbean.getUSER_PHONE())
				.append("', USER_FMPHONE = '"+userbean.getUSER_FMPHONE())
				.append("' where USER_ID_PK = '")
				.append(User_id)
				.append("';").toString();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return false;
	}
}
