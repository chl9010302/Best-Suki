package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.TestDetailBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestDetailAdd {

	TestDetailBean testdetailbean;
	Connection conn = null;
	Statement stmt = null;
	private StringProperty testdetail_id_pk;
	private StringProperty subtitle;
	private ObservableList<TestDetailAdd> testdetailadd = FXCollections.observableArrayList();
	
	public ObservableList<TestDetailAdd> gettestdetailadd() {
		select();
		return testdetailadd;
	}
	
	public StringProperty getTestdetail_id_pk() {
		return testdetail_id_pk;
	}

	public StringProperty getSubtitle() {
		return subtitle;
	}

	public TestDetailAdd() { }
	
	public TestDetailAdd(String TESTDETAIL_ID_PK, String TESTDETAIL_SUBTITLE) {
		this.testdetail_id_pk = new SimpleStringProperty(TESTDETAIL_ID_PK);
		this.subtitle = new SimpleStringProperty(TESTDETAIL_SUBTITLE);
	}

	public TestDetailAdd(TestDetailBean testdetailbean) {
		insertTestDetail(testdetailbean);
	}
	public boolean insertTestDetail(TestDetailBean testdetailbean) {
		String insertsql = "INSERT INTO TESTDETAIL_TB(TESTDETAIL_ID_PK, TESTDETAIL_DATA1, TESTDETAIL_DATA2, TESTDETAIL_DATA3, TESTDETAIL_DATA4, TESTDETAIL_DATA5, TESTDETAIL_ANSWER, TESTDETAIL_IMAGE, TESTDETAIL_SUBTITLE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = null;
		this.testdetailbean = testdetailbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setString(1, testdetailbean.getTESTDETAIL_ID_PK());
			pstmt.setString(2, testdetailbean.getTESTDETAIL_DATA1());
			pstmt.setString(3, testdetailbean.getTESTDETAIL_DATA2());
			pstmt.setString(4, testdetailbean.getTESTDETAIL_DATA3());
			pstmt.setString(5, testdetailbean.getTESTDETAIL_DATA4());
			pstmt.setString(6, testdetailbean.getTESTDETAIL_DATA5());
			pstmt.setString(7, testdetailbean.getTESTDETAIL_ANSWER());
			pstmt.setString(8, testdetailbean.getTESTDETAIL_IMAGE());	
			pstmt.setString(9, testdetailbean.getTESTDETAIL_SUBTITLE());	
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
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
	
	public boolean select() {
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("SELECT * FROM TESTDETAIL_TB").append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				testdetailadd.add(new TestDetailAdd((String) rs.getString("TESTDETAIL_ID_PK"), (String) rs.getString("TESTDETAIL_SUBTITLE")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void delete(int id) {
		StringBuilder sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		String sql = sb.append("DELETE FROM TESTDETAIL_TB where TESTDETAIL_ID_PK = ").append(id).append(";").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
