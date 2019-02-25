package DBController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import DBModel.UserBean;
import usingstaticfunction.DBConnectionKeeping;

public class SelectNowUser {
	private DBConnectionKeeping dbConnectionKeeping;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private UserBean userbean;
	private StringBuilder sb;
	private String sql;
	public UserBean getSelectUser(String login_Id) {
		userbean = new UserBean();
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		sb = new StringBuilder();
		sql = sb.append("SELECT * FROM  "+config.StaticProperty.getuser_tb()+" WHERE").append(" USER_ID_PK = '").append(login_Id).append("';")
				.toString();
		con = usingstaticfunction.DBConnectionKeeping.con;
		stmt = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userbean.setUSER_ID_PK(rs.getString("USER_ID_PK"));
				userbean.setUSER_PASSWORD(rs.getString("USER_PASSWORD"));
				userbean.setUSER_NAME(rs.getString("USER_NAME"));
				userbean.setUSER_ADDRESS(rs.getString("USER_ADDRESS"));
				userbean.setUSER_SCHOOLNAME(rs.getString("USER_SCHOOLNAME"));
				userbean.setUSER_AGE(rs.getString("USER_AGE"));
				userbean.setUSER_GENDER(rs.getString("USER_GENDER"));
				userbean.setUSER_PHONE(rs.getString("USER_PHONE"));
				userbean.setUSER_FMPHONE(rs.getString("USER_FMPHONE"));
				userbean.setUSER_LOGINSESSION(rs.getString("USER_LOGINSESSION"));
				userbean.setUSER_TEACHERSESSION(rs.getString("USER_TEACHERSESSION"));
				userbean.setCLASS_NUMBER(rs.getString("CLASS_NUMBER"));
			} return userbean;
		} catch (Exception e) { e.printStackTrace();} return null;
	}
}
