package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DBModel.TestResultBean;

public class TestResultAdd {
	private TestResultBean testresultbean;
	public static ArrayList<String> testresult;
	public static String answer_result="";
	private Connection conn = null;
	private String sql;
	private PreparedStatement pstmt;
	public TestResultAdd() {
	}
	public TestResultAdd(TestResultBean testresultbean) {
		insertTestResult(testresultbean);
	}
	public boolean insertTestResult(TestResultBean testresultbean) {
		sql = "INSERT INTO "+config.StaticProperty.gettestresult_tb()+"(TESTRESULT_ID_PK, TEST_ID, TESTRESULT_ID, TESTRESULT_ANSWER, TESTRESULT_WRITER, TESTRESULT_TIME) VALUES(?, ?, ?, ?, ?, now());";
		pstmt = null;
		try {
			conn = application.DBConnection.getDBConection();		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testresultbean.getTESTRESULT_ID_PK());
			pstmt.setString(2, testresultbean.getTEST_ID());
			pstmt.setString(3, testresultbean.getTESTRESULT_ID());
			pstmt.setString(4, testresultbean.getTESTRESULT_ANSWER());
			pstmt.setString(5, testresultbean.getTESTRESULT_WRITER());
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
}
