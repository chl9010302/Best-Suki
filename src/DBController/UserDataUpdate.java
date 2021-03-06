package DBController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.UserBean;
import ViewController.CommonController;
import usingstaticfunction.DBConnectionKeeping;

public class UserDataUpdate {
	private Statement stmt;
	private DBConnectionKeeping dbConnectionKeeping;
	private Connection con;
	private StringBuilder sb;
	private String sql;
	public UserDataUpdate() { }
	public UserDataUpdate(UserBean userbean, String User_id) {
		UserUpdate(userbean, User_id);
	}
	public boolean UserUpdate(UserBean userbean, String User_id) {
		if(userbean.getUSER_PASSWORD() == null) {
			userbean.setUSER_PASSWORD(CommonController.selectcontent(User_id, "USER_PASSWORD", config.StaticProperty.getuser_tb(), "USER_ID_PK"));
		}
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		stmt = null;
		try {
		con = usingstaticfunction.DBConnectionKeeping.con;
		stmt = con.createStatement();
		sb = new StringBuilder();
		sql = sb.append("UPDATE "+config.StaticProperty.getuser_tb()+" SET")
				.append(" USER_ID_PK = '"+userbean.getUSER_ID_PK())
				.append("', USER_PASSWORD = '"+userbean.getUSER_PASSWORD())
				.append("', USER_NAME = '"+userbean.getUSER_NAME())
				.append("', USER_ADDRESS = '"+userbean.getUSER_ADDRESS())
				.append("', USER_SCHOOLNAME = '"+userbean.getUSER_SCHOOLNAME())
				.append("', USER_AGE = '"+userbean.getUSER_AGE())
				.append("', USER_GENDER = '"+userbean.getUSER_GENDER())
				.append("', USER_PHONE = '"+userbean.getUSER_PHONE())
				.append("', USER_FMPHONE = '"+userbean.getUSER_FMPHONE())
				.append("' WHERE USER_ID_PK = '")
				.append(User_id)
				.append("';").toString();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {e.printStackTrace(); } return false;
	}
}
