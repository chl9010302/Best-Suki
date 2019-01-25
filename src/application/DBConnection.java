package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/academymangementdb?serverTimezone=UTC", "root", "root");
			st = con.createStatement();
			
		 SqlTest sqlTest = new SqlTest(con, "temp_table");
		 
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("DB 연결오류 :" + e.getMessage() );
		}
	
	
	}
	
	public boolean isAdmin(String adminID, String adminPassword)  {
	//관리자 계정 확인
	
		try 
		{
			String SQL = "SELECT * FROM ADMIN WHERE adminID = '"+adminID +
					"'and adminPassword= '"+ adminPassword +"'" ;
			rs = st.executeQuery(SQL);
			if(rs.next())
			{
				return true;
			}
			
		}catch(Exception e) {	
			System.out.println("DB 검색오류");
			
		}
		return true;

	}
}
