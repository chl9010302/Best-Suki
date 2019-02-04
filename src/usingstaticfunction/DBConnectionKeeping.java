package usingstaticfunction;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionKeeping {
	public static Connection con;
	
	public DBConnectionKeeping() {
		con = getDBConection();
	}
	public Connection getDBConection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/academymanagementdb?serverTimezone=UTC",
					"root", "root");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결오류 :" + e.getMessage());
		} finally {
			
		}
		return null;
	}
}
