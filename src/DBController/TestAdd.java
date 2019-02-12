package DBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.TestBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import usingstaticfunction.DBConnectionKeeping;

public class TestAdd {
	TestBean testbean;
	Connection conn = null;
	Statement stmt = null;
	public static String test_id = "";
	private StringProperty test_id_pk, test_subtitle, test_writer, test_time;
	private Button test_btndetail;
	public StringProperty getTest_writer() {
		return test_writer;
	}
	public void setTest_writer(StringProperty test_writer) {
		this.test_writer = test_writer;
	}
	public StringProperty getTest_time() {
		return test_time;
	}
	public void setTest_time(StringProperty test_time) {
		this.test_time = test_time;
	}
	private ObservableList<TestAdd> testadd = FXCollections.observableArrayList();
	public ObservableList<TestAdd> gettestadd() {
		select();
		return testadd;
	}
	public StringProperty getTest_id_pk() {
		return test_id_pk;
	}
	public StringProperty getTest_Subtitle() {
		return test_subtitle;
	}
	public Button getTest_btndetail() {
		return test_btndetail;
	}
	public void setTest_btndetail(Button test_btndetail) {
		this.test_btndetail = test_btndetail;
	}
	public TestAdd() { }
	public TestAdd(String TEST_ID_PK, String TEST_SUBTITLE, String TEST_WRITER, String TEST_TIME) {
		this.test_id_pk = new SimpleStringProperty(TEST_ID_PK);
		this.test_subtitle = new SimpleStringProperty(TEST_SUBTITLE);
		this.test_writer = new SimpleStringProperty(TEST_WRITER);
		this.test_time = new SimpleStringProperty(TEST_TIME);
		this.test_btndetail = new Button("Details");
		test_btndetail.setOnAction(event -> {
			test_id = ""; // 초기화
			test_id = test_id_pk.get();
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
			} catch (IOException e) {e.printStackTrace(); }
		});
	}
	public TestAdd(TestBean testbean) {
		insertTestDetail(testbean);
	}
	public boolean updateTestDetail(TestBean testbean) {
		DBConnectionKeeping dbConnectionKeeping;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		Statement stmt = null;
		try {
			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			stmt = con.createStatement();
			String updatesql = "UPDATE "+config.StaticProperty.gettest_tb()
			+" SET TEST_SUBTITLE = '" + testbean.getTEST_SUBTITLE() 
			+ "', TEST_ID1_FK = '" + testbean.getTEST_ID1_FK() 
			+ "', TEST_ID2_FK = '" + testbean.getTEST_ID2_FK() 
			+ "', TEST_ID3_FK = '" + testbean.getTEST_ID3_FK() 
			+ "', TEST_ID4_FK = '" + testbean.getTEST_ID4_FK() 
			+ "', TEST_ID5_FK = '" + testbean.getTEST_ID5_FK() 
			+ "', TEST_ID6_FK = '" + testbean.getTEST_ID6_FK() 
			+ "', TEST_ID7_FK = '" + testbean.getTEST_ID7_FK() 
			+ "', TEST_ID8_FK = '" + testbean.getTEST_ID8_FK() 
			+ "', TEST_ID9_FK = '" + testbean.getTEST_ID9_FK() 
			+ "', TEST_ID10_FK = '" + testbean.getTEST_ID10_FK() 
			+ "', TEST_ID11_FK = '" + testbean.getTEST_ID11_FK() 
			+ "', TEST_ID12_FK = '" + testbean.getTEST_ID12_FK() 
			+ "', TEST_ID13_FK = '" + testbean.getTEST_ID13_FK()
			+ "', TEST_ID14_FK = '" + testbean.getTEST_ID14_FK() 
			+ "' WHERE TEST_ID_PK = '" + testbean.getTEST_ID_PK() + "';";
			stmt.executeUpdate(updatesql);
			return true;
		} catch (SQLException e) { } return false;
	}
	public boolean insertTestDetail(TestBean testbean) {
		String insertsql = "INSERT INTO "+config.StaticProperty.gettest_tb()+"(TEST_ID_PK, TEST_SUBTITLE, TEST_ID1_FK, TEST_ID2_FK, TEST_ID3_FK, TEST_ID4_FK, TEST_ID5_FK, TEST_ID6_FK, TEST_ID7_FK, TEST_ID8_FK, TEST_ID9_FK, TEST_ID10_FK, TEST_ID11_FK, TEST_ID12_FK, TEST_ID13_FK, TEST_ID14_FK, TEST_WRITER, TEST_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now());";
		PreparedStatement pstmt = null;
		this.testbean = testbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setString(1, testbean.getTEST_ID_PK());
			pstmt.setString(2, testbean.getTEST_SUBTITLE());
			pstmt.setString(3, testbean.getTEST_ID1_FK());
			pstmt.setString(4, testbean.getTEST_ID2_FK());
			pstmt.setString(5, testbean.getTEST_ID3_FK());
			pstmt.setString(6, testbean.getTEST_ID4_FK());
			pstmt.setString(7, testbean.getTEST_ID5_FK());
			pstmt.setString(8, testbean.getTEST_ID6_FK());
			pstmt.setString(9, testbean.getTEST_ID7_FK());
			pstmt.setString(10, testbean.getTEST_ID8_FK());
			pstmt.setString(11, testbean.getTEST_ID9_FK());
			pstmt.setString(12, testbean.getTEST_ID10_FK());
			pstmt.setString(13, testbean.getTEST_ID11_FK());
			pstmt.setString(14, testbean.getTEST_ID12_FK());
			pstmt.setString(15, testbean.getTEST_ID13_FK());
			pstmt.setString(16, testbean.getTEST_ID14_FK());
			pstmt.setString(17, testbean.getTEST_WRITER());
			pstmt.executeUpdate();
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {
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
	public boolean select() {
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("SELECT * FROM "+config.StaticProperty.gettest_tb()).append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				testadd.add(new TestAdd((String) rs.getString("TEST_ID_PK"), (String) rs.getString("TEST_SUBTITLE"), (String) rs.getString("TEST_WRITER"),(String) rs.getString("TEST_TIME").substring(0,10)));
			}
		} catch (SQLException e) { } return false;
	}
	public void delete(String id) {
		StringBuilder sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		String sql = sb.append("DELETE FROM "+config.StaticProperty.gettest_tb()+" WHERE TEST_ID_PK = '").append(id).append("';").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { }
	}
	public String selectSubtitle(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TEST_SUBTITLE FROM "+config.StaticProperty.gettest_tb()+" WHERE TEST_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TEST_SUBTITLE") != null)
					result = rs.getString("TEST_SUBTITLE");
			}
		}catch(Exception e) { } return result;
	}
}
