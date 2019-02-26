package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ViewController.CommonController;
import usingstaticfunction.DBConnectionKeeping;

public class UserLogin {
	public static String logintime;
	private Connection conn = null;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Statement stmt;
	private DBConnectionKeeping dbConnectionKeeping;
	private String sql;
	private StringBuilder sb;
	private int check_loginsession, check_datesession;
	public int loginCheck(String user_id, String user_pw) {
		check_loginsession = 0;
		sql = "SELECT USER_ID_PK FROM "+config.StaticProperty.getuser_tb()+" WHERE USER_ID_PK = ? AND USER_PASSWORD = ?";
		try {
			conn = application.DBConnection.getDBConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("USER_ID_PK") != null) {
					sql = "UPDATE "+config.StaticProperty.getuser_tb()+" SET USER_LOGINSESSION = 1 WHERE USER_ID_PK = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user_id);
					check_loginsession = pstmt.executeUpdate();
					if (check_loginsession == 1)
						check_loginsession = date_tb(user_id);
					else {
					}
				}
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
			}
		} return check_loginsession;
	}
	private int date_tb(String user_id) throws SQLException {
		check_datesession = 0;
		sql = "INSERT INTO "+config.StaticProperty.getdate_tb()+"(DATE_ID_PK, USER_ID, DATE_LOGINTIME)VALUES(?,?,now())";
		pstmt = conn.prepareStatement(sql);
		logintime = CommonController.MakeId();
		pstmt.setString(1, CommonController.MakeId());
		pstmt.setString(2, user_id);
		check_datesession = pstmt.executeUpdate();
		return check_datesession;
	}
	public void logout(String user_id) throws SQLException {
		stmt = null;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		conn = usingstaticfunction.DBConnectionKeeping.con;
		stmt = conn.createStatement();
		sb = new StringBuilder();
		sql = sb.append("UPDATE "+config.StaticProperty.getuser_tb()+" SET").append(" USER_LOGINSESSION = 0").append(" WHERE USER_ID_PK = '")
				.append(user_id).append("';").toString();
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) { }
	}
	public void logout2(String user_id) throws SQLException {
		sb = new StringBuilder();
		stmt = null;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		conn = usingstaticfunction.DBConnectionKeeping.con;
		stmt = conn.createStatement();
		sql = sb.append("UPDATE "+config.StaticProperty.getdate_tb()+" SET DATE_LOGOUTTIME = now() WHERE DATE_ID_PK = '").append(logintime).append("';").toString();
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
		}finally {
			if(stmt.isClosed()) {
				logintime = "";
			}
		}
	}
}
