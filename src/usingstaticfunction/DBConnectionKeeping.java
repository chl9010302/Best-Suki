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
			Class.forName(config.StaticProperty.getdriver());
			con = DriverManager.getConnection(config.StaticProperty.geturl(), config.StaticProperty.getusername(), config.StaticProperty.getpassword());
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결오류 :" + e.getMessage());
		} finally {
		}
		return null;
	}
}
