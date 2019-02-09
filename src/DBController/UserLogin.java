package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import usingstaticfunction.DBConnectionKeeping;

public class UserLogin {

	Connection conn = null;
	ResultSet rs;
	PreparedStatement pstmt;
	String sql;
	public static String logintime;

	public int loginCheck(String user_id, String user_pw) {
		int i = 0;
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
					i = pstmt.executeUpdate();
					if (i == 1)
						i = date_tb(user_id);
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
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return i;
	}

	private int date_tb(String user_id) throws SQLException {
		int i = 0;
		sql = "INSERT INTO "+config.StaticProperty.getdate_tb()+"(DATE_ID_PK, USER_ID, DATE_LOGINTIME)VALUES(?,?,now())";
		pstmt = conn.prepareStatement(sql);
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		String dateidpk = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		logintime = dateidpk;
		pstmt.setString(1, dateidpk);
		pstmt.setString(2, user_id);
		i = pstmt.executeUpdate();
		return i;
	}

	public void logout(String user_id) throws SQLException {
		DBConnectionKeeping dbConnectionKeeping;  
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		Connection con = usingstaticfunction.DBConnectionKeeping.con;
		Statement stmt = null;
		stmt = con.createStatement();
		StringBuilder sb = new StringBuilder();
		String sql = sb.append("UPDATE "+config.StaticProperty.getuser_tb()+" SET").append(" USER_LOGINSESSION = 0").append(" where USER_ID_PK = '")
				.append(user_id).append("';").toString();
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stmt.isClosed()) {
				
			}
		}
	}
	public void logout2(String user_id) throws SQLException {
		DBConnectionKeeping dbConnectionKeeping;  
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();

		Connection con = usingstaticfunction.DBConnectionKeeping.con;
		Statement stmt = null;
		stmt = con.createStatement();
		StringBuilder sb = new StringBuilder();
		

		String sql = sb.append("UPDATE "+config.StaticProperty.getdate_tb()+" SET DATE_LOGOUTTIME = now() where DATE_ID_PK = '").append(logintime).append("';").toString();
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stmt.isClosed()) {
				logintime = "";
			}
		}
	}
}
