package usingstaticfunction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDetailFunction {
	static public String makeTestDetailKey(String subtitle) {
		try {
			DBConnectionKeeping dbConnectionKeeping;
			if (usingstaticfunction.DBConnectionKeeping.con == null)
				dbConnectionKeeping = new DBConnectionKeeping();

			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			String selectsql = "SELECT * FROM " + config.StaticProperty.gettestdetail_tb() + " ORDER BY TESTDETAIL_ID_PK DESC LIMIT 1;";
			String sqlresult = "";
			String test_detailpkey = "";

			Statement stmt = null;
			stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(selectsql);
			
			if (rs.next())
				sqlresult = rs.getString(1);
			if (sqlresult.length() > 0) {
				String increasenum = sqlresult.substring(sqlresult.lastIndexOf("-") + 1);
				int numInt = Integer.parseInt(increasenum);
				numInt++;
				increasenum = String.valueOf(numInt);
				if (increasenum.length() == 1)
					increasenum = "0000" + increasenum;
				if (increasenum.length() == 2)
					increasenum = "000" + increasenum;
				if (increasenum.length() == 3)
					increasenum = "00" + increasenum;
				if (increasenum.length() == 4)
					increasenum = "0" + increasenum;

				test_detailpkey = subtitle + "-" + increasenum;
			} else {
				test_detailpkey = subtitle + "-" + "00001";
			}
			
			return test_detailpkey;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}
		return "Error";
	}
}
