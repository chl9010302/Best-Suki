package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.TestDetailBean;
import DBModel.UserBean;

public class TestDetailAdd {

	TestDetailBean testdetailbean;
	Connection conn = null;
	Statement stmt = null;

	public TestDetailAdd(TestDetailBean testdetailbean) {
		insertTestDetail(testdetailbean);
	}
	public boolean insertTestDetail(TestDetailBean testdetailbean) {
		String insertsql = "INSERT INTO TestDetail(TestDetail_pkey, TestDetail_Data, TestDetail_Data2, TestDetail_Data3, TestDetail_Data4, TestDetail_Data5, TestDetail_CorrectAnswer, TestDetail_Image) VALUSE(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = null;
		this.testdetailbean = testdetailbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setString(1, testdetailbean.getTestDetail_pkey());
			pstmt.setString(2, testdetailbean.getTestDetail_Data());
			pstmt.setString(3, testdetailbean.getTestDetail_Data2());
			pstmt.setString(4, testdetailbean.getTestDetail_Data3());
			pstmt.setString(5, testdetailbean.getTestDetail_Data4());
			pstmt.setString(6, testdetailbean.getTestDetail_Data5());
			pstmt.setString(7, testdetailbean.getTestDetail_CorrectAnswer());
			pstmt.setString(8, testdetailbean.getTestDetail_Image());	
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {

			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e3) {

			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
