package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBController.UserJoin;
import DBModel.UserBean;

public class DBConnection {
	public DBConnection() {
		Connection con;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/academymangementdb?serverTimezone=UTC",
					"root", "root");
			st = con.createStatement();

			SqlTest sqlTest = new SqlTest(con, "temp_table");

		
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결오류 :" + e.getMessage());
		} finally {
			try {
				if(st!=null)
					st.close();
			} catch (SQLException e2) {
			
			}
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e3) {
			
			}
		}

	}

	static public Connection getDBConection() {
		Connection con;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/academymangementdb?serverTimezone=UTC",
					"root", "root");
		
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결오류 :" + e.getMessage());
		} finally {
			try {
				if(st!=null)
					st.close();
			} catch (SQLException e2) {
			
			}
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e3) {
			
			}
		}
			return null;
	}
/*
	public boolean isAdmin(String adminID, String adminPassword) {
		// 관리자 계정 확인
		try {
			String SQL = "SELECT * FROM ADMIN WHERE adminID = '" + adminID + "'and adminPassword= '" + adminPassword
					+ "'";
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("DB 검색오류");

		}
		return true;

	}
*/
}
