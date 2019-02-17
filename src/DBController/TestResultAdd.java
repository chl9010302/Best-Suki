package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBModel.TestResultBean;

public class TestResultAdd {
	TestResultBean testresultbean;
	public static ArrayList<String> testresult;
	static Statement stmt = null;
	public static Connection conn = null;
	public TestResultAdd() {
	}
	public TestResultAdd(TestResultBean testresultbean) {
		insertTestResult(testresultbean);
	}
	public boolean insertTestResult(TestResultBean testresultbean) {
		String insertsql = "INSERT INTO "+config.StaticProperty.gettestresult_tb()+"(TESTRESULT_ID_PK, TEST_ID, TESTRESULT_ID1, TESTRESULT_ID2, TESTRESULT_ID3, TESTRESULT_ID4, TESTRESULT_ID5, TESTRESULT_ANSWER1, TESTRESULT_ANSWER2, TESTRESULT_ANSWER3, TESTRESULT_ANSWER4, TESTRESULT_ANSWER5, TESTRESULT_WRITER, TESTRESULT_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now());";
		PreparedStatement pstmt = null;
		try {
			conn = application.DBConnection.getDBConection();		
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setString(1, testresultbean.getTESTRESULT_ID_PK());
			pstmt.setString(2, testresultbean.getTEST_ID());
			pstmt.setString(3, testresultbean.getTESTRESULT_ID1());
			pstmt.setString(4, testresultbean.getTESTRESULT_ID2());
			pstmt.setString(5, testresultbean.getTESTRESULT_ID3());
			pstmt.setString(6, testresultbean.getTESTRESULT_ID4());
			pstmt.setString(7, testresultbean.getTESTRESULT_ID5());
			pstmt.setString(8, testresultbean.getTESTRESULT_ANSWER1());
			pstmt.setString(9, testresultbean.getTESTRESULT_ANSWER2());
			pstmt.setString(10, testresultbean.getTESTRESULT_ANSWER3());
			pstmt.setString(11, testresultbean.getTESTRESULT_ANSWER4());
			pstmt.setString(12, testresultbean.getTESTRESULT_ANSWER5());
			pstmt.setString(13, testresultbean.getTESTRESULT_WRITER());
			pstmt.executeUpdate();
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e2) { }
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e3) { }
		} return false;
	}
	public static String selectgetresultanswer(String id, int i) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTRESULT_ANSWER" + i + " FROM "+config.StaticProperty.gettestresult_tb()+" WHERE TESTRESULT_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTRESULT_ANSWER" + i) != null)
					result = rs.getString("TESTRESULT_ANSWER" + i);
			}
		}catch(Exception e) { e.printStackTrace();} return result;
	}
	public static String selectgetresultid(String id, int i) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTRESULT_ID" + i + " FROM "+config.StaticProperty.gettestresult_tb()+" WHERE TESTRESULT_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTRESULT_ID" + i) != null)
					result = rs.getString("TESTRESULT_ID" + i);
			}
		}catch(Exception e) { e.printStackTrace();} return result;
	}
}
