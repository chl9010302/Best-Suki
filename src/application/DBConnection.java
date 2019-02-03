package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public DBConnection() {
		Connection con;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/academymanagementdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", 
					"root", "root");
			st = con.createStatement();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결오류 :" + e.getMessage());
		} finally {
			try {
				if(st!=null)
					st.close();
			} catch (SQLException e2) {	}
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e3) {	}
		}
	}
	static public Connection getDBConection() {
		Connection con;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/academymanagementdb?serverTimezone=UTC",
					"root", "root");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결오류 :" + e.getMessage());
		} finally {
			try {
				if(st!=null)
					st.close();
			} catch (SQLException e2) {	}
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e3) {	}
		}
			return null;
	}
}
