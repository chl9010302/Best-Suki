package usingstaticfunction;

import java.sql.Connection;

public class TestDetailFunction {
		static public String makeTestDetailKey(){
			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			String selectsqil = "SELECT * FROM TestDetail ORDER BY id DESC LIMIT 1;";
			
			
			
			return null;
		}
}
